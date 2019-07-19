package io.olaph.slack.client.spring.group.im

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.im.ErrorImCloseResponse
import io.olaph.slack.dto.jackson.group.im.SlackImCloseRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImCloseResponse
import io.olaph.slack.dto.jackson.group.im.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

internal class DefaultImCloseMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("Im.close Failure")
    fun imCloseFailure() {
        val response = ErrorImCloseResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "im.close", response)
        val verifier = Verifier(response)

        DefaultImCloseMethod("", mockTemplate)
                .with(SlackImCloseRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("Im.close Success")
    fun imCloseSuccess() {
        val response = SuccessfulImCloseResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "im.close", response)
        val verifier = Verifier(response)

        DefaultImCloseMethod("", mockTemplate)
                .with(SlackImCloseRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
