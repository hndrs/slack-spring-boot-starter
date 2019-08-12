package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorGetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulGetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.GetPresenceRequest
import com.kreait.slack.api.contract.jackson.group.users.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultUsersGetPresenceMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @DisplayName("Successful users.getPresence Method")
    @Test
    fun usersGetPresenceSuccess() {
        val response = SuccessfulGetPresenceResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.getPresence", response)
        val verifier = Verifier(response)


        DefaultUsersGetPresenceMethod("", mockTemplate)
                .with(GetPresenceRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @DisplayName("Error users.getPresence Method")
    @Test
    fun usersGetPresenceError() {
        val response = ErrorGetPresenceResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.getPresence", response)
        val verifier = Verifier(response)


        DefaultUsersGetPresenceMethod("", mockTemplate)
                .with(GetPresenceRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
