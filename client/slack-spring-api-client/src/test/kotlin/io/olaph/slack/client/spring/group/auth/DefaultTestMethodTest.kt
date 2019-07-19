package io.olaph.slack.client.spring.group.auth

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.auth.ErrorAuthTestResponse
import io.olaph.slack.dto.jackson.group.auth.SuccessfulAuthTestResponse
import io.olaph.slack.dto.jackson.group.auth.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultTestMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("auth.test Failure")
    fun authTestFailure() {
        val response = ErrorAuthTestResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "auth.test", response)
        val verifier = Verifier(response)

        DefaultTestMethod("", mockTemplate)
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("auth.Test Success")
    fun authTestSuccess() {
        val response = SuccessfulAuthTestResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "auth.test", response)
        val verifier = Verifier(response)

        DefaultTestMethod("", mockTemplate)
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
