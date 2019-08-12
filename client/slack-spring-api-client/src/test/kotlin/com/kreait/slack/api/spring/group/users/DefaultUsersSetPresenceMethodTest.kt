package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorSetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.SetPresenceRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulSetPresenceResponse
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

        val response = SuccessfulSetPresenceResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.setPresence", response)
        val verifier = Verifier(response)

        DefaultUsersSetPresenceMethod("", mockTemplate)
                .with(SetPresenceRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("users.setPresence Failure")
    fun userSetPresenceFailure() {

        val response = ErrorSetPresenceResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.setPresence", response)
        val verifier = Verifier(response)

        DefaultUsersSetPresenceMethod("", mockTemplate)
                .with(SetPresenceRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
