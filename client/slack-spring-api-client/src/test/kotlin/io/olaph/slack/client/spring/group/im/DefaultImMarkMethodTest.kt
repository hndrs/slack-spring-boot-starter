package io.olaph.slack.client.spring.group.im

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.im.ErrorImMarkResponse
import io.olaph.slack.dto.jackson.group.im.SlackImMarkRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImMarkResponse
import io.olaph.slack.dto.jackson.group.im.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

internal class DefaultImMarkMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("Im.Mark Failure")
    fun imMarkFailure() {
        val response = ErrorImMarkResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "im.mark", response)
        val verifier = Verifier(response)
        DefaultImMarkMethod("", mockTemplate)
                .with(SlackImMarkRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("Im.Mark Success")
    fun imMarkSuccess() {
        val response = SuccessfulImMarkResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "im.mark", response)
        val verifier = Verifier(response)

        DefaultImMarkMethod("", mockTemplate)
                .with(SlackImMarkRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
