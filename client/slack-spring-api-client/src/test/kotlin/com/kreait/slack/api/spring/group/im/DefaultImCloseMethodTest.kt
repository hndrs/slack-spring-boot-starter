package com.kreait.slack.api.spring.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImCloseResponse
import com.kreait.slack.api.contract.jackson.group.im.SlackImCloseRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImCloseResponse
import com.kreait.slack.api.contract.jackson.group.im.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
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
