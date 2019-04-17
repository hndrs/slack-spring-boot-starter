package io.olaph.slack.client.spring.group.oauth

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.oauth.ErrorOauthAccessResponse
import io.olaph.slack.dto.jackson.group.oauth.OauthAccessRequest
import io.olaph.slack.dto.jackson.group.oauth.SuccessFullOauthAccessResponse
import io.olaph.slack.dto.jackson.group.oauth.sample
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultOauthAccessMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("oauth.access Failure")
    fun oauthAccessFailure() {
        val response = ErrorOauthAccessResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "oauth.access?client_id=&client_secret=&code=")

        DefaultOauthAccessMethod(mockTemplate)
                .with(OauthAccessRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }

    @Test
    @DisplayName("oauth.access Success")
    fun oauthAccessSuccess() {
        val response = SuccessFullOauthAccessResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "oauth.access?client_id=&client_secret=&code=")

        DefaultOauthAccessMethod(mockTemplate)
                .with(OauthAccessRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }
}
