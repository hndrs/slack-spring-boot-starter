package com.kreait.slack.api.spring.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsKickRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationKickResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationKickResponse
import com.kreait.slack.api.contract.jackson.group.conversations.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class ConversationsKickTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @DisplayName("conversations.kick Success")
    @Test
    fun conversationsKickSuccess() {
        val response = SuccessfulConversationKickResponse.sample()

        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.kick", response)
        val verifier = Verifier(response)

        DefaultConverstationsKickMethod("", mockTemplate)
                .with(ConversationsKickRequest.sample())
                .onSuccess { verifier.set(it) }
                .onFailure { }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @DisplayName("conversations.kick Failure")
    @Test
    fun conversationsKickFailure() {
        val response = ErrorConversationKickResponse.sample()

        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.kick", response)
        val verifier = Verifier(response)

        DefaultConverstationsKickMethod("", mockTemplate)
                .with(ConversationsKickRequest.sample())
                .onSuccess { }
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()

    }

}
