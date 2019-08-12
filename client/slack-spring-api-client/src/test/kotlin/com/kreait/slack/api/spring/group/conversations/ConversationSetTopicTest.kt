package com.kreait.slack.api.spring.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsSetTopicRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationSetTopicResponse
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class ConversationSetTopicTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @DisplayName("Set-Topic Success")
    @Test
    fun conversationSetPurposeSuccess() {
        val response = SuccessfulConversationSetTopicResponse.sample()

        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.setTopic", response)
        val verifier = Verifier(response)

        DefaultConversationsSetTopicMethod("", mockTemplate)
                .with(ConversationsSetTopicRequest.sample())
                .onSuccess { verifier.set(it) }
                .onFailure { }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }

    @DisplayName("Set-Topic Failure")
    @Test
    fun conversationSetTopicFailure() {
        val response = ErrorConversationSetTopicResponse.sample()

        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.setTopic", response)
        val verifier = Verifier(response)

        DefaultConversationsSetTopicMethod("", mockTemplate)
                .with(ConversationsSetTopicRequest.sample())
                .onFailure { verifier.set(it) }
                .onSuccess { }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }
}
