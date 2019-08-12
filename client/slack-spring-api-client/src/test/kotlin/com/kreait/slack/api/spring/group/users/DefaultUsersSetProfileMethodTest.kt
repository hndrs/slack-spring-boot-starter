package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorSetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulSetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultUsersSetProfileMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("Users.setProfile Failure")
    fun UserSetProfileFailure() {
        val response = ErrorSetProfileResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.profile.set", response)
        val verifier = Verifier(response)

        DefaultUsersSetProfileMethod("", mockTemplate)
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("Users.setProfile Success")
    fun UserSetProfileSuccess() {
        val response = SuccessfulSetProfileResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.profile.set", response)
        val verifier = Verifier(response)

        DefaultUsersSetProfileMethod("", mockTemplate)
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
