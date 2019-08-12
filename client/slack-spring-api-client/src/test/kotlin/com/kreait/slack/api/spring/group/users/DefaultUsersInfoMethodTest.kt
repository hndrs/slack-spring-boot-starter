package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersInfoResponse
import com.kreait.slack.api.contract.jackson.group.users.SlackUserInfoRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersInfoResponse
import com.kreait.slack.api.contract.jackson.group.users.sample
import com.kreait.slack.api.spring.group.users.DefaultUsersInfoMethod
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultUsersInfoMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("Users.info Failure")
    fun UserInfoFailure() {
        val response = ErrorUsersInfoResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.info", response)
        val verifier = Verifier(response)

        DefaultUsersInfoMethod("", mockTemplate)
                .with(SlackUserInfoRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("Users.info Success")
    fun UserInfoSuccess() {
        val response = SuccessfulUsersInfoResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.info", response)
        val verifier = Verifier(response)

        DefaultUsersInfoMethod("", mockTemplate)
                .with(SlackUserInfoRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
