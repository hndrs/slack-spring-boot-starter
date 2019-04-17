package io.olaph.slack.client.spring.group.auth

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.dto.jackson.group.auth.AuthRevokeRequest
import io.olaph.slack.dto.jackson.group.auth.ErrorAuthRevokeResponse
import io.olaph.slack.dto.jackson.group.auth.SuccessfulAuthRevokeResponse
import io.olaph.slack.dto.jackson.group.auth.sample
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultRevokeMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplate()
    }

    @Test
    @DisplayName("auth.revoke Failure")
    fun authRevokeFailure() {
        val response = ErrorAuthRevokeResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "auth.revoke?test=true")

        DefaultRevokeMethod("", mockTemplate)
                .with(AuthRevokeRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }

    @Test
    @DisplayName("auth.revoke Success")
    fun authRevokeSuccess() {
        val response = SuccessfulAuthRevokeResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "auth.revoke?test=true")

        DefaultRevokeMethod("", mockTemplate)
                .with(AuthRevokeRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }
}