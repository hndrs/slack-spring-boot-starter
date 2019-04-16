package io.olaph.slack.client.spring.group

import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpRequest
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.StreamUtils
import org.springframework.web.client.RestTemplate
import org.springframework.http.client.BufferingClientHttpRequestFactory
import org.springframework.http.client.SimpleClientHttpRequestFactory

import org.springframework.web.util.UriComponentsBuilder
import java.io.IOException
import java.net.URI
import java.nio.charset.Charset.forName


class SlackRequestBuilder<T>(private val token: String? = null, private val restTemplate: RestTemplate) {

    var body: Any? = null
    lateinit var uri: URI
    lateinit var responseType: Class<T>


    init {
        val mappingJackson2HttpMessageConverter = MappingJackson2HttpMessageConverter()
        restTemplate.interceptors = listOf(RequestResponseLoggingInterceptor())
        mappingJackson2HttpMessageConverter.supportedMediaTypes = listOf(MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED)
        restTemplate.messageConverters.add(mappingJackson2HttpMessageConverter)
    }

    /**
     * with body
     */
    fun with(body: Any): SlackRequestBuilder<T> {
        this.body = body
        return this
    }

    fun toMethod(methodName: String): SlackRequestBuilder<T> {
        this.uri = URI.create("https://slack.com/api/$methodName")
        return this
    }

    fun returnAsType(responseType: Class<T>): SlackRequestBuilder<T> {
        this.responseType = responseType
        return this
    }

    internal fun postWithJsonBody(): ResponseEntity<T> {
        val requestEntity = RequestEntity(this.body, slackHeaders(listOf("application/json")), HttpMethod.POST, this.uri)
        return restTemplate.exchange(requestEntity, responseType)
    }

    internal fun postUrlEncoded(params: Map<String, String>): ResponseEntity<T> {

        val builder = UriComponentsBuilder.fromHttpUrl(this.uri.toString())

        params.forEach { key, value -> builder.queryParam(key, value) }

        val requestEntity = HttpEntity<Any>(slackHeaders(listOf("application/x-www-form-urlencoded")))
        return restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                requestEntity,
                this.responseType)
    }

    private fun slackHeaders(contentType: List<String>): LinkedMultiValueMap<String, String> {
        val httpHeaders = LinkedMultiValueMap<String, String>()

        //set token if available
        token?.let { httpHeaders[HttpHeaders.AUTHORIZATION] = listOf("Bearer $it") }

        httpHeaders[HttpHeaders.CONTENT_TYPE] = contentType
        return httpHeaders
    }
}

class RequestResponseLoggingInterceptor : ClientHttpRequestInterceptor {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Throws(IOException::class)
    override fun intercept(request: HttpRequest, body: ByteArray, execution: ClientHttpRequestExecution): ClientHttpResponse {
        logRequest(request, body)
        val response = execution.execute(request, body)
        logResponse(response)
        return response
    }

    @Throws(IOException::class)
    private fun logRequest(request: HttpRequest, body: ByteArray) {
        when {
            log.isDebugEnabled ->
                log.debug("\nURI         : {}\nMethod      : {}\nHeaders     : {}\nRequest body: {}", request.uri, request.method, request.headers, String(body, forName("UTF-8")))
        }
    }

    @Throws(IOException::class)
    private fun logResponse(response: ClientHttpResponse) {
        when {
            log.isDebugEnabled ->
                log.debug("\nStatus code  : {}\nStatus text  : {}\nHeaders      : {}\nResponse body: {}", response.statusCode, response.statusText, response.headers, StreamUtils.copyToString(response.body, forName("UTF-8")))
        }
    }
}
