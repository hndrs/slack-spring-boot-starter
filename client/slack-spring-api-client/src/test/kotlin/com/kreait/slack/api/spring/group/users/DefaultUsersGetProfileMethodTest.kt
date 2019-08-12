package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.GetProfileRequest
import com.kreait.slack.api.contract.jackson.group.users.sample
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
        val response = ErrorGetProfileResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.profile.get", response)
        val verifier = Verifier(response)

        DefaultUsersGetProfileMethod("", mockTemplate)
                .with(GetProfileRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("Users.getProfile Success")
    fun UserGetProfileSuccess() {
        val response = SuccessfulGetProfileResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.profile.get", response)
        val verifier = Verifier(response)

        DefaultUsersGetProfileMethod("", mockTemplate)
                .with(GetProfileRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
