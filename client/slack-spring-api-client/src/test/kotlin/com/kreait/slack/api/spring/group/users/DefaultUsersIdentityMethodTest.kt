package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorIdentityResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulIdentityResponse
import com.kreait.slack.api.contract.jackson.group.users.sample
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
        val response = ErrorIdentityResponse.sample()
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
        val response = SuccessfulIdentityResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.identity", response)
        val verifier = Verifier(response)

        DefaultUsersIdentityMethod("", mockTemplate)
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
