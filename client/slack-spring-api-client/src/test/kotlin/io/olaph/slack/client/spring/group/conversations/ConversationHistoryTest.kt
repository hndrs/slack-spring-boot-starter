package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.conversations.ConversationsHistoryRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationHistoryResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationHistoryResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
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
    fun conversationOpenFailure() {
        val response = ErrorConversationHistoryResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "conversations.history?channel=&cursor=&inclusive=false")
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
    fun conversationOpenSuccess() {
        val response = SuccessfulConversationHistoryResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "conversations.history?channel=&cursor=&inclusive=false")
        val verifier = Verifier(response)

        DefaultConversationsHistoryMethod("", mockTemplate)
                .with(ConversationsHistoryRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
