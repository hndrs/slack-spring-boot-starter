package com.kreait.slack.api.spring.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ChatMeMessageRequest
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatMeMessageResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatMeMessageResponse
import com.kreait.slack.api.contract.jackson.group.chat.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

internal class DefaultMeMessageMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("chat.meMessage Failure")
    fun chatMeMessageFailure() {
        val response = ErrorChatMeMessageResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "chat.meMessage", response)
        val verifier = Verifier(response)

        DefaultMeMessageMethod("", mockTemplate)
                .with(ChatMeMessageRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("chat.meMessage Success")
    fun chatMeMessageSuccess() {
        val response = SuccessfulChatMeMessageResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "chat.meMessage", response)
        val verifier = Verifier(response)

        DefaultMeMessageMethod("", mockTemplate)
                .with(ChatMeMessageRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
