package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersSetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.SlackUsersSetPresenceRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersSetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultUsersSetPresenceMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("users.setPresence Success")
    fun userSetPresenceSuccess() {

        val response = SuccessfulUsersSetPresenceResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.setPresence", response)
        val verifier = Verifier(response)

        DefaultUsersSetPresenceMethod("", mockTemplate)
                .with(SlackUsersSetPresenceRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("users.setPresence Failure")
    fun userSetPresenceFailure() {

        val response = ErrorUsersSetPresenceResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.setPresence", response)
        val verifier = Verifier(response)

        DefaultUsersSetPresenceMethod("", mockTemplate)
                .with(SlackUsersSetPresenceRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
