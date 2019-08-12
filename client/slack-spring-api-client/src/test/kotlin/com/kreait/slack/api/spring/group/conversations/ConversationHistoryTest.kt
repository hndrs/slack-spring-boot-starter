package com.kreait.slack.api.spring.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsHistoryRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationHistoryResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationHistoryResponse
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class ConversationHistoryTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("conversations.history Failure")
    fun conversationHistoryFailure() {
        val response = ErrorConversationHistoryResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.history", response)
        val verifier = Verifier(response)

        DefaultConversationsHistoryMethod("", mockTemplate)
                .with(ConversationsHistoryRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("conversations.history Success")
    fun conversationHistoryMockConversationsHistoryUnitTestSuccess() {
        val response = SuccessfulConversationHistoryResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.history", response)
        val verifier = Verifier(response)

        DefaultConversationsHistoryMethod("", mockTemplate)
                .with(ConversationsHistoryRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
