package io.olaph.slack.client.spring.group.users

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.users.ErrorUsersSetPresenceResponse
import io.olaph.slack.dto.jackson.group.users.SlackUsersSetPresenceRequest
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersSetPresenceResponse
import io.olaph.slack.dto.jackson.group.users.sample
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "users.setPresence")
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "users.setPresence")
        val verifier = Verifier(response)

        DefaultUsersSetPresenceMethod("", mockTemplate)
                .with(SlackUsersSetPresenceRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}