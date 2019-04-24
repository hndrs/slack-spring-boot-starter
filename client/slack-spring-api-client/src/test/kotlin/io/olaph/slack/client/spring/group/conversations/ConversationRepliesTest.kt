package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.conversations.ConversationsRepliesRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationRepliesResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationRepliesResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "conversations.replies?channel=SampleChannelId&ts=SampleTimestamp")
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "conversations.replies?channel=SampleChannelId&ts=SampleTimestamp")
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
