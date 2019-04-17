package io.olaph.slack.client.spring.group.auth

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.dto.jackson.group.auth.ErrorAuthTestResponse
import io.olaph.slack.dto.jackson.group.auth.SuccessfulAuthTestResponse
import io.olaph.slack.dto.jackson.group.auth.sample
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultTestMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplate()
    }

    @Test
    @DisplayName("auth.test Failure")
    fun authTestFailure() {
        val response = ErrorAuthTestResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "auth.test")

        DefaultTestMethod("", mockTemplate)
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }

    @Test
    @DisplayName("auth.Test Success")
    fun authTestSuccess() {
        val response = SuccessfulAuthTestResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "auth.test")

        DefaultTestMethod("", mockTemplate)
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }
}