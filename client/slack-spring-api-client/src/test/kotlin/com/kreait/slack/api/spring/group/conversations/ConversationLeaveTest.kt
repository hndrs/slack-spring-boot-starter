package com.kreait.slack.api.spring.group.conversations

import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsLeaveRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationLeaveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationLeaveResponse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class ConversationLeaveTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("conversations.leave Failure")
    fun conversationLeaveFailure() {
        val response = ErrorConversationLeaveResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.leave", response)
        val verifier = Verifier(response)

        DefaultConversationsLeaveMethod("", mockTemplate)
                .with(ConversationsLeaveRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("conversations.leave Success")
    fun conversationLeaveSuccess() {
        val response = SuccessfulConversationLeaveResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.leave", response)
        val verifier = Verifier(response)

        DefaultConversationsLeaveMethod("", mockTemplate)
                .with(ConversationsLeaveRequest.sample())
                .onSuccess { verifier.set(it) }
                .onFailure { }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
