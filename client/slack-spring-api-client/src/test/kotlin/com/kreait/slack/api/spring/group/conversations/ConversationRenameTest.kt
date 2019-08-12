package com.kreait.slack.api.spring.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsRenameRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationsRenameResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationsRenameResponse
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class ConversationRenameTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("conversations.rename Failure")
    fun conversationRenameFailure() {
        val response = ErrorConversationsRenameResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.rename", response)
        val verifier = Verifier(response)

        DefaultConversationsRenameMethod("", mockTemplate)
                .with(ConversationsRenameRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("conversations.Rename Success")
    fun conversationRenameSuccess() {
        val response = SuccessfulConversationsRenameResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.rename", response)
        val verifier = Verifier(response)

        DefaultConversationsRenameMethod("", mockTemplate)
                .with(ConversationsRenameRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
