package com.kreait.slack.api.spring.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImMarkResponse
import com.kreait.slack.api.contract.jackson.group.im.SlackImMarkRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImMarkResponse
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
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
