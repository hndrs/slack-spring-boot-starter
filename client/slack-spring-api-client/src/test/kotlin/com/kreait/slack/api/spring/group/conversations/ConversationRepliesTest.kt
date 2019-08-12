package com.kreait.slack.api.spring.group.conversations

import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsRepliesRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationRepliesResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationRepliesResponse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class ConversationRepliesTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("conversations.replies Failure")
    fun conversationLeaveFailure() {
        val response = ErrorConversationRepliesResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.replies", response)
        val verifier = Verifier(response)

        DefaultConversationsRepliesMethod("", mockTemplate)
                .with(ConversationsRepliesRequest.sample().copy(channelId = "SampleChannelId", timestamp = "SampleTimestamp"))
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("conversations.replies Success")
    fun conversationLeaveSuccess() {
        val response = SuccessfulConversationRepliesResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.replies", response)
        val verifier = Verifier(response)

        DefaultConversationsRepliesMethod("", mockTemplate)
                .with(ConversationsRepliesRequest.sample().copy(channelId = "SampleChannelId", timestamp = "SampleTimestamp"))
                .onSuccess { verifier.set(it) }
                .onFailure { }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
