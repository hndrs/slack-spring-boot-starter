package io.olaph.slack.client.spring.group.users

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.users.ErrorUsersGetProfileResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersGetProfileResponse
import io.olaph.slack.dto.jackson.group.users.UsersGetProfileRequest
import io.olaph.slack.dto.jackson.group.users.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultUsersGetProfileMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("Users.getProfile Failure")
    fun UserGetProfileFailure() {
        val response = ErrorUsersGetProfileResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "users.profile.get")
        val verifier = Verifier(response)

        DefaultUsersGetProfileMethod("", mockTemplate)
                .with(UsersGetProfileRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("Users.getProfile Success")
    fun UserGetProfileSuccess() {
        val response = SuccessfulUsersGetProfileResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "users.profile.get")
        val verifier = Verifier(response)

        DefaultUsersGetProfileMethod("", mockTemplate)
                .with(UsersGetProfileRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
