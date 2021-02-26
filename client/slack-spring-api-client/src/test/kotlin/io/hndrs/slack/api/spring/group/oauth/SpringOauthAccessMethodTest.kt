package io.hndrs.slack.api.spring.group.oauth

import io.hndrs.slack.api.contract.jackson.group.oauth.ErrorAccessResponse
import io.hndrs.slack.api.contract.jackson.group.oauth.AccessRequest
import io.hndrs.slack.api.contract.jackson.group.oauth.SuccessfullAccessResponse
import io.hndrs.slack.api.contract.jackson.group.oauth.sample
import io.hndrs.slack.api.spring.MockServerHelper
import io.hndrs.slack.api.spring.Verifier
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class SpringOauthAccessMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("oauth.access Failure")
    fun oauthAccessFailure() {
        val response = ErrorAccessResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "oauth.v2.access", response)
        val verifier = Verifier(response)

        SpringOauthAccessMethod(mockTemplate)
                .with(AccessRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("oauth.access Success")
    fun oauthAccessSuccess() {
        val response = SuccessfullAccessResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "oauth.v2.access", response)
        val verifier = Verifier(response)

        SpringOauthAccessMethod(mockTemplate)
                .with(AccessRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
