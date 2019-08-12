package com.kreait.slack.api.spring.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsListRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationListResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationListResponse
import com.kreait.slack.api.contract.jackson.group.conversations.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
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
