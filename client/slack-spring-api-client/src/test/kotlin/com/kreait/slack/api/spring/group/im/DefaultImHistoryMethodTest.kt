package com.kreait.slack.api.spring.group.im

import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.contract.jackson.group.im.ErrorImHistoryResponse
import com.kreait.slack.api.contract.jackson.group.im.SlackImHistoryRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImHistoryResponse
import com.kreait.slack.api.spring.group.im.DefaultImHistoryMethod
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
