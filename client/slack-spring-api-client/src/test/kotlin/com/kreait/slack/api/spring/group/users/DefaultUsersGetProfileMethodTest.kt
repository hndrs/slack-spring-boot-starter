package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.UsersGetProfileRequest
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.profile.get", response)
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.profile.get", response)
        val verifier = Verifier(response)

        DefaultUsersGetProfileMethod("", mockTemplate)
                .with(UsersGetProfileRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
