package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.conversations.ConversationsSetPurposeRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationSetPurposeResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationSetPurposeResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class ConversationSetPurposeTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @DisplayName("Set-Purpose Success")
    @Test
    fun conversationSetPurposeSuccess() {
        val response = SuccessfulConversationSetPurposeResponse.sample()

        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "conversations.setPurpose")
        val verifier = Verifier(response)

        DefaultConversationsSetPurposeMethod("", mockTemplate)
                .with(ConversationsSetPurposeRequest.sample())
                .onSuccess { verifier.set(it) }
                .onFailure {  }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }

    @DisplayName("Set-Purpose Failure")
    @Test
    fun conversationSetPurposeFailure() {
        val response = ErrorConversationSetPurposeResponse.sample()

        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "conversations.setPurpose")
        val verifier = Verifier(response)

        DefaultConversationsSetPurposeMethod("", mockTemplate)
                .with(ConversationsSetPurposeRequest.sample())
                .onFailure { verifier.set(it) }
                .onSuccess {  }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }
}