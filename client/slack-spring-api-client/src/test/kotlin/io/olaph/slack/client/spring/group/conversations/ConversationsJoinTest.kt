package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.conversations.ConversationJoinRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationJoinResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationJoinResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "conversations.join")
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "conversations.join")
        val verifier = Verifier(response)

        DefaultConversationsJoinMethod("", mockTemplate)
                .with(ConversationJoinRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}