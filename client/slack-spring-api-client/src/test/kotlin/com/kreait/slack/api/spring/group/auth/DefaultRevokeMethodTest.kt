package com.kreait.slack.api.spring.group.auth

import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.contract.jackson.group.auth.AuthRevokeRequest
import com.kreait.slack.api.contract.jackson.group.auth.ErrorAuthRevokeResponse
import com.kreait.slack.api.contract.jackson.group.auth.SuccessfulAuthRevokeResponse
import com.kreait.slack.api.spring.group.auth.DefaultRevokeMethod
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultRevokeMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("auth.revoke Failure")
    fun authRevokeFailure() {
        val response = ErrorAuthRevokeResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "auth.revoke", response)
        val verifier = Verifier(response)

        DefaultRevokeMethod("", mockTemplate)
                .with(AuthRevokeRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("auth.revoke Success")
    fun authRevokeSuccess() {
        val response = SuccessfulAuthRevokeResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "auth.revoke", response)
        val verifier = Verifier(response)

        DefaultRevokeMethod("", mockTemplate)
                .with(AuthRevokeRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
