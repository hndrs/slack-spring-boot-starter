package com.kreait.slack.api.spring.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsInfoRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationsInfoResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationsInfoResponse
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class ConversationsInfoTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("conversations.info Failure")
    fun conversationListFailure() {
        val response = ErrorConversationsInfoResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.info", response)
        val verifier = Verifier(response)

        DefaultConversationsInfoMethod("", mockTemplate)
                .with(ConversationsInfoRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("conversations.info Success")
    fun conversationListSuccess() {
        val response = SuccessfulConversationsInfoResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.info", response)
        val verifier = Verifier(response)

        DefaultConversationsInfoMethod("", mockTemplate)
                .with(ConversationsInfoRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
