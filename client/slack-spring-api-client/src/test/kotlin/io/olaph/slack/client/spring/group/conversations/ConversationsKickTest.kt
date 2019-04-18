package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.conversations.ConversationsKickRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationKickResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationKickResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
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

        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "conversations.kick")
        val verifier = Verifier(response)

        DefaultConverstationsKickMethod("", mockTemplate)
                .with(ConversationsKickRequest.sample())
                .onSuccess { verifier.set(it) }
                .onFailure {  }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @DisplayName("conversations.kick Failure")
    @Test
    fun conversationsKickFailure() {
        val response = ErrorConversationKickResponse.sample()

        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "conversations.kick")
        val verifier = Verifier(response)

        DefaultConverstationsKickMethod("", mockTemplate)
                .with(ConversationsKickRequest.sample())
                .onSuccess {  }
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()

    }

}