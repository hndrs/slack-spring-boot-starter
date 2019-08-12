package com.kreait.slack.api.spring.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationUnarchiveRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationUnarchiveResponse
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class ConversationUnarchiveTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("conversations.unarchive Failure")
    fun conversationUnarchiveFailure() {
        val response = ErrorConversationUnarchiveResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.unarchive", response)
        val verifier = Verifier(response)

        DefaultConversationsUnarchiveMethod("", mockTemplate)
                .with(ConversationUnarchiveRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("conversations.unarchive Success")
    fun conversationUnarchiveSuccess() {
        val response = SuccessfulConversationUnarchiveResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.unarchive", response)
        val verifier = Verifier(response)

        DefaultConversationsUnarchiveMethod("", mockTemplate)
                .with(ConversationUnarchiveRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
