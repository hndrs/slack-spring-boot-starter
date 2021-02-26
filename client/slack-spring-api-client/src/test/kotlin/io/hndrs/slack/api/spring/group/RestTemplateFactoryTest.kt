package io.hndrs.slack.api.spring.group

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.apache.http.HttpHost
import org.apache.http.impl.client.HttpClientBuilder
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Resttemplate Factory Tests")
internal class RestTemplateFactoryTest {


    @Test
    @DisplayName("Slack RestTemplate Default Configuration ")
    fun slackRestTemplate() {
        val mockHttpClientBuilder = mock<HttpClientBuilder> { }
        io.hndrs.slack.api.spring.group.RestTemplateFactory.clientFactory(mockHttpClientBuilder)

        verify(mockHttpClientBuilder, never()).setProxy(any())
        verify(mockHttpClientBuilder, never()).setSSLSocketFactory(any())
        verify(mockHttpClientBuilder, never()).setSSLHostnameVerifier(any())
        verify(mockHttpClientBuilder, times(1)).build()
    }

    @Test
    @DisplayName("Slack RestTemplate Proxy Configuration ")
    fun slackRestTemplateWithProxy() {
        val proxyHostPropertyName = "slack.proxyHost"

        val mockHttpClientBuilder = mock<HttpClientBuilder> { }
        System.setProperty(proxyHostPropertyName, "http://localhost:8080")
        io.hndrs.slack.api.spring.group.RestTemplateFactory.clientFactory(mockHttpClientBuilder)

        verify(mockHttpClientBuilder, times(1)).setProxy(HttpHost.create("http://localhost:8080"))

        verify(mockHttpClientBuilder, never()).setSSLSocketFactory(any())
        verify(mockHttpClientBuilder, never()).setSSLHostnameVerifier(any())
        verify(mockHttpClientBuilder, times(1)).build()

        System.clearProperty(proxyHostPropertyName)
    }

    @Test
    @DisplayName("Slack RestTemplate SSL Configuration")
    fun slackRestTemplateWithCustomSSLTrue() {
        val sslPropertyName = "slack.development.ssl.accept-self-signed"

        val mockHttpClientBuilder = mock<HttpClientBuilder> { }
        System.setProperty(sslPropertyName, "true")

        io.hndrs.slack.api.spring.group.RestTemplateFactory.clientFactory(mockHttpClientBuilder)

        verify(mockHttpClientBuilder, never()).setProxy(any())
        verify(mockHttpClientBuilder, times(1)).setSSLSocketFactory(any())
        verify(mockHttpClientBuilder, times(1)).setSSLHostnameVerifier(any())
        verify(mockHttpClientBuilder, times(1)).build()

        System.clearProperty(sslPropertyName)
    }

    @Test
    @DisplayName("Slack RestTemplate SSL Configuration set to false")
    fun slackRestTemplateWithCustomSSLfalse() {
        val sslPropertyName = "slack.development.ssl.accept-self-signed"

        val mockHttpClientBuilder = mock<HttpClientBuilder> { }
        System.setProperty(sslPropertyName, "false")

        io.hndrs.slack.api.spring.group.RestTemplateFactory.clientFactory(mockHttpClientBuilder)

        verify(mockHttpClientBuilder, never()).setProxy(any())
        verify(mockHttpClientBuilder, never()).setSSLSocketFactory(any())
        verify(mockHttpClientBuilder, never()).setSSLHostnameVerifier(any())
        verify(mockHttpClientBuilder, times(1)).build()

        System.clearProperty(sslPropertyName)
    }
}
