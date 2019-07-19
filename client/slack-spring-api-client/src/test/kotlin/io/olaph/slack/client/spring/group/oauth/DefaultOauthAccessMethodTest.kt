package io.olaph.slack.client.spring.group.oauth

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.oauth.ErrorOauthAccessResponse
import io.olaph.slack.dto.jackson.group.oauth.OauthAccessRequest
import io.olaph.slack.dto.jackson.group.oauth.SuccessFullOauthAccessResponse
import io.olaph.slack.dto.jackson.group.oauth.sample
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "oauth.access", response)
        val verifier = Verifier(response)

        DefaultOauthAccessMethod(mockTemplate)
                .with(OauthAccessRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("oauth.access Success")
    fun oauthAccessSuccess() {
        val response = SuccessFullOauthAccessResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "oauth.access", response)
        val verifier = Verifier(response)

        DefaultOauthAccessMethod(mockTemplate)
                .with(OauthAccessRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
