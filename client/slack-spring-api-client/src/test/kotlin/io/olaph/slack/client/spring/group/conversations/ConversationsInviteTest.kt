package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.conversations.ConversationsInviteRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationInviteResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationInviteResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "conversations.invite")
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "conversations.invite")
        val verifier = Verifier(response)

        DefaultConversationsInviteMethod("", mockTemplate)
                .with(ConversationsInviteRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
