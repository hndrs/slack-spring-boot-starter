package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersIdentityResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersIdentityResponse
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultUsersIdentityMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("Users.identity Failure")
    fun UserIdentityFailure() {
        val response = ErrorUsersIdentityResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.identity", response)
        val verifier = Verifier(response)

        DefaultUsersIdentityMethod("", mockTemplate)
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("Users.identity Success")
    fun UserIdentitySuccess() {
        val response = SuccessfulUsersIdentityResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.identity", response)
        val verifier = Verifier(response)

        DefaultUsersIdentityMethod("", mockTemplate)
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
