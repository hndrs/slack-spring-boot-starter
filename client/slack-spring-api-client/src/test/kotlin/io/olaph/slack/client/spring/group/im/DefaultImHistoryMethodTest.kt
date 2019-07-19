package io.olaph.slack.client.spring.group.im

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.im.ErrorImHistoryResponse
import io.olaph.slack.dto.jackson.group.im.SlackImHistoryRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImHistoryResponse
import io.olaph.slack.dto.jackson.group.im.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

internal class DefaultImHistoryMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("im.history Failure")
    fun imHistoryFailure() {
        val response = ErrorImHistoryResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "im.history", response)
        val verifier = Verifier(response)
        DefaultImHistoryMethod("", mockTemplate)
                .with(SlackImHistoryRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("im.history Success")
    fun imHistorySuccess() {
        val response = SuccessfulImHistoryResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "im.history", response)
        val verifier = Verifier(response)

        DefaultImHistoryMethod("", mockTemplate)
                .with(SlackImHistoryRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}