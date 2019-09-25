package com.kreait.slack.api.spring.group

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.FormHttpMessageConverter
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import java.net.URI

/**
 * Builds slack-api compliant requests
 *
 * @param T the response type
 * @property token authorization token
 * @property restTemplate template which is used to make requests
 */
class SlackRequestBuilder<T>(private val token: String? = null, private val restTemplate: RestTemplate) {

    var body: Any? = null
    lateinit var uri: URI
    lateinit var responseType: Class<T>

    /**
     * with body
     */
    fun with(body: Any): SlackRequestBuilder<T> {
        this.body = body
        return this
    }

    /**
     * creates the api-method URI
     */
    fun toMethod(methodName: String): SlackRequestBuilder<T> {
        this.uri = URI.create("https://slack.com/api/$methodName")
        return this
    }

    /**
     * sets the return-type
     */
    fun returnAsType(responseType: Class<T>): SlackRequestBuilder<T> {
        this.responseType = responseType
        return this
    }

    internal fun postWithJsonBody(): ResponseEntity<T> {
        val requestEntity = RequestEntity(this.body, slackHeaders(listOf("application/json")), HttpMethod.POST, this.uri)
        return restTemplate.exchange(requestEntity, responseType)
    }

    internal fun postUrlEncoded(params: Map<String, String>, contentType: List<String> = listOf("application/x-www-form-urlencoded")): ResponseEntity<T> {
        val map = LinkedMultiValueMap<String, String>()
        params.forEach { (key, value) -> map.add(key, value) }
        val req = HttpEntity<MultiValueMap<String, String>>(map, slackHeaders(contentType))
        return restTemplate.exchange(this.uri, HttpMethod.POST, req, responseType)
    }

    internal fun postMultipartFormdata(): ResponseEntity<T> {
        restTemplate.messageConverters.add(FormHttpMessageConverter())
        val requestEntity = HttpEntity(this.body, slackHeaders(listOf(MediaType.MULTIPART_FORM_DATA_VALUE)))

        return restTemplate.exchange(
                uri, HttpMethod.POST, requestEntity,
                responseType)
    }

    private fun slackHeaders(contentType: List<String>): LinkedMultiValueMap<String, String> {
        val httpHeaders = LinkedMultiValueMap<String, String>()
        //set token if available
        token?.let { httpHeaders[HttpHeaders.AUTHORIZATION] = listOf("Bearer $it") }

        httpHeaders[HttpHeaders.CONTENT_TYPE] = contentType
        return httpHeaders
    }
}
