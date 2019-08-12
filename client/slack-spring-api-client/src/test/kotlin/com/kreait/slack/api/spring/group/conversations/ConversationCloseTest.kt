package com.kreait.slack.api.spring.group.conversations

import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationCloseRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationCloseResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationCloseResponse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class ConversationCloseTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("conversations.close Failure")
    fun conversationCloseFailure() {
        val response = ErrorConversationCloseResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.close", response)
        val verifier = Verifier(response)

        DefaultConversationsCloseMethod("", mockTemplate)
                .with(ConversationCloseRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("conversations.close Success")
    fun conversationCloseSuccess() {
        val response = SuccessfulConversationCloseResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.close", response)
        val verifier = Verifier(response)

        DefaultConversationsCloseMethod("", mockTemplate)
                .with(ConversationCloseRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
