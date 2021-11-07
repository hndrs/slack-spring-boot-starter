package io.hndrs.slack.api.spring.group

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import io.hndrs.slack.api.spring.group.respond.SlackResponseErrorHandler
import org.apache.http.HttpHost
import org.apache.http.conn.ssl.NoopHostnameVerifier
import org.apache.http.conn.ssl.SSLConnectionSocketFactory
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.impl.client.HttpClients
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.http.converter.FormHttpMessageConverter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate
import java.security.cert.X509Certificate


/**
 * Holds the configured [RestTemplate] instance that is compliant with the slack api
 */
object RestTemplateFactory {

    private const val PROXY_PROPERTY_NAME = "slack.proxyHost"
    private const val SSL_PROPERTY_NAME = "slack.development.ssl.accept-self-signed"
    private val slackApiRestTemplate = RestTemplate(clientFactory(HttpClients.custom()))
    private val slackResponseRestTemplate = RestTemplate()

    init {

        val objectMapper = Jackson2ObjectMapperBuilder
            .json()
            .serializationInclusion(JsonInclude.Include.NON_NULL)
            .failOnUnknownProperties(false)
            .build<ObjectMapper>()
        slackApiRestTemplate.messageConverters =
            listOf(MappingJackson2HttpMessageConverter(objectMapper), FormHttpMessageConverter())
        slackResponseRestTemplate.messageConverters =
            listOf(MappingJackson2HttpMessageConverter(objectMapper), FormHttpMessageConverter())
        slackResponseRestTemplate.errorHandler = SlackResponseErrorHandler()
    }

    /**
     *
     */
    internal fun clientFactory(builder: HttpClientBuilder): HttpComponentsClientHttpRequestFactory {

        System.getProperty(PROXY_PROPERTY_NAME)?.let {
            builder.setProxy(HttpHost.create(it))
        }

        (System.getProperty(SSL_PROPERTY_NAME)?.toBoolean())?.let { allowUnsafeCertificates ->
            if (allowUnsafeCertificates) {

                val acceptingTrustStrategy = { _: Array<X509Certificate>, _: String -> true }

                val sslContext = org.apache.http.ssl.SSLContexts.custom()
                    .loadTrustMaterial(null, acceptingTrustStrategy)
                    .build()

                val csf = SSLConnectionSocketFactory(sslContext)
                builder.setSSLSocketFactory(csf)
                builder.setSSLHostnameVerifier(NoopHostnameVerifier())
            }
        }

        return HttpComponentsClientHttpRequestFactory(builder.build())
    }


    /**
     * gets a slack compliant slackApiRestTemplate
     */
    fun slackTemplate() = slackApiRestTemplate

    /**
     *  gets a slackApiRestTemplate which logs errors without interrupting
     */
    fun slackResponseTemplate() = slackResponseRestTemplate

}
