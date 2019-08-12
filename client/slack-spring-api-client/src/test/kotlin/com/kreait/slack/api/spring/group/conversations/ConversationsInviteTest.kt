package com.kreait.slack.api.spring.group.conversations

import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsInviteRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationInviteResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationInviteResponse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class ConversationsInviteTest() {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("conversations.invite Failure")
    fun conversationInvite() {
        val response = ErrorConversationInviteResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.invite", response)
        val verifier = Verifier(response)

        DefaultConversationsInviteMethod("", mockTemplate)
                .with(ConversationsInviteRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("conversations.invite Success")
    fun conversationInviteSuccess() {
        val response = SuccessfulConversationInviteResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.invite", response)
        val verifier = Verifier(response)

        DefaultConversationsInviteMethod("", mockTemplate)
                .with(ConversationsInviteRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
