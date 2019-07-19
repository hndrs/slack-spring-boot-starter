package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.conversations.ConversationCloseRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationCloseResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationCloseResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
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
