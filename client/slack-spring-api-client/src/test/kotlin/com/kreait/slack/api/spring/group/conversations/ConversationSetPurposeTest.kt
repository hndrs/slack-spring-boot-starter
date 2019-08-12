package com.kreait.slack.api.spring.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsSetPurposeRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.conversations.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
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

        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate,  "conversations.setPurpose",response)
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

        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate,  "conversations.setPurpose",response)
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
