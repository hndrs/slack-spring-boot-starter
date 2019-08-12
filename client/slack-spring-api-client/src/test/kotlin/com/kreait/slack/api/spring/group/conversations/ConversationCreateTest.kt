package com.kreait.slack.api.spring.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationCreateRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationCreateResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationCreateResponse
import com.kreait.slack.api.contract.jackson.group.conversations.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.create", response)
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.create", response)
        val verifier = Verifier(response)

        DefaultConversationsCreateMethod("", mockTemplate)
                .with(ConversationCreateRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
