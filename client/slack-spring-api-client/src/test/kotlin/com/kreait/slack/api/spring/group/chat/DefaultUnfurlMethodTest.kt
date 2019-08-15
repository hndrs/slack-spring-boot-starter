package com.kreait.slack.api.spring.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ChatUnfurlRequest
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatUnfurlResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatUnfurlResponse
import com.kreait.slack.api.contract.jackson.group.chat.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

internal class DefaultUnfurlMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("chat.Unfurl Failure")
    fun chatUnfurlFailure() {
        val response = ErrorChatUnfurlResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "chat.unfurl", response)
        val verifier = Verifier(response)

        DefaultUnfurlMethod("", mockTemplate)
                .with(ChatUnfurlRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("chat.Unfurl Success")
    fun chatUnfurlSuccess() {
        val response = SuccessfulChatUnfurlResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "chat.unfurl", response)
        val verifier = Verifier(response)

        DefaultUnfurlMethod("", mockTemplate)
                .with(ChatUnfurlRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
