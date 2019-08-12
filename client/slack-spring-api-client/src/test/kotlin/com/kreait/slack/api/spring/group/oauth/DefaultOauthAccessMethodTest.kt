package com.kreait.slack.api.spring.group.oauth

import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.contract.jackson.group.oauth.ErrorOauthAccessResponse
import com.kreait.slack.api.contract.jackson.group.oauth.OauthAccessRequest
import com.kreait.slack.api.contract.jackson.group.oauth.SuccessFullOauthAccessResponse
import com.kreait.slack.api.contract.jackson.group.oauth.sample
import com.kreait.slack.api.spring.group.oauth.DefaultOauthAccessMethod
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
