package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.conversations.ConversationCreateRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationCreateResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationCreateResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class ConversationCreateTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("conversations.create Failure")
    fun conversationCreateFailure() {
        val response = ErrorConversationCreateResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "conversations.create")
        val verifier = Verifier(response)

        DefaultConversationsCreateMethod("", mockTemplate)
                .with(ConversationCreateRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("conversations.Create Success")
    fun conversationCreateSuccess() {
        val response = SuccessfulConversationCreateResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "conversations.create")
        val verifier = Verifier(response)

        DefaultConversationsCreateMethod("", mockTemplate)
                .with(ConversationCreateRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
