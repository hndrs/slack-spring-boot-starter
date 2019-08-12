package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUserConversationsResponse
import com.kreait.slack.api.contract.jackson.group.users.SlackUserConversationListRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUserConversationsResponse
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultUserConversationsMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("Users.conversations Failure")
    fun UserConversationsFailure() {
        val response = ErrorUserConversationsResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.conversations", response)
        val verifier = Verifier(response)

        DefaultUserConversationsMethod("", mockTemplate)
                .with(SlackUserConversationListRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("Users.conversations Success")
    fun UserConversationsSuccess() {
        val response = SuccessfulUserConversationsResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.conversations", response)
        val verifier = Verifier(response)

        DefaultUserConversationsMethod("", mockTemplate)
                .with(SlackUserConversationListRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
