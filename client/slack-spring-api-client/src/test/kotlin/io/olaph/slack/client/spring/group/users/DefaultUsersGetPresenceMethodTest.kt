package io.olaph.slack.client.spring.group.users

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.users.ErrorUsersGetPresenceResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersGetPresenceResponse
import io.olaph.slack.dto.jackson.group.users.UsersGetPresenceRequest
import io.olaph.slack.dto.jackson.group.users.sample
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
        val response = SuccessfulUsersGetPresenceResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "users.getPresence")
        val verifier = Verifier(response)


        DefaultUsersGetPresenceMethod("", mockTemplate)
                .with(UsersGetPresenceRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @DisplayName("Error users.getPresence Method")
    @Test
    fun usersGetPresenceError() {
        val response = ErrorUsersGetPresenceResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "users.getPresence")
        val verifier = Verifier(response)


        DefaultUsersGetPresenceMethod("", mockTemplate)
                .with(UsersGetPresenceRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
