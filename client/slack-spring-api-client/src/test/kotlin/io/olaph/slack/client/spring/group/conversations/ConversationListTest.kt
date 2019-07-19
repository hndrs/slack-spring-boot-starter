package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.conversations.ConversationsListRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationListResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationListResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class ConversationListTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("conversations.list Failure")
    fun conversationListFailure() {
        val response = ErrorConversationListResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.list", response)
        val verifier = Verifier(response)

        DefaultConversationsListMethod("", mockTemplate)
                .with(ConversationsListRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("conversations.list Success")
    fun conversationListSuccess() {
        val response = SuccessfulConversationListResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.list", response)
        val verifier = Verifier(response)

        DefaultConversationsListMethod("", mockTemplate)
                .with(ConversationsListRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
