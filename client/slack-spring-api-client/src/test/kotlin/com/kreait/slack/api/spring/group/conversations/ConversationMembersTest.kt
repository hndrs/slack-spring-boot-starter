package com.kreait.slack.api.spring.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationMembersRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationMembersResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationMembersResponse
import com.kreait.slack.api.contract.jackson.group.conversations.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class ConversationMembersTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("conversations.members Failure")
    fun conversationMembersFailure() {
        val response = ErrorConversationMembersResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.members", response)
        val verifier = Verifier(response)

        DefaultConversationsMembersMethod("", mockTemplate)
                .with(ConversationMembersRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("conversations.members Success")
    fun conversationMembersSuccess() {
        val response = SuccessfulConversationMembersResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.members", response)
        val verifier = Verifier(response)

        DefaultConversationsMembersMethod("", mockTemplate)
                .with(ConversationMembersRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
