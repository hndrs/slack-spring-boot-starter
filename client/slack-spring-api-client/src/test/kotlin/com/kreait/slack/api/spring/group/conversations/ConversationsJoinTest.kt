package com.kreait.slack.api.spring.group.conversations

import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationJoinRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationJoinResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationJoinResponse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class ConversationsJoinTest {

    lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("conversations.join Failure")
    fun conversationJoinFailure() {
        val response = ErrorConversationJoinResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.join", response)
        val verifier = Verifier(response)

        DefaultConversationsJoinMethod("", mockTemplate)
                .with(ConversationJoinRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("conversations.join Success")
    fun conversationJoinSuccess() {
        val response = SuccessfulConversationJoinResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.join", response)
        val verifier = Verifier(response)

        DefaultConversationsJoinMethod("", mockTemplate)
                .with(ConversationJoinRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
