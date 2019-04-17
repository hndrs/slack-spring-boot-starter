package io.olaph.slack.client.spring.group.users

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.users.ErrorUsersInfoResponse
import io.olaph.slack.dto.jackson.group.users.SlackUserInfoRequest
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersInfoResponse
import io.olaph.slack.dto.jackson.group.users.sample
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "users.info?user=&include_locale=false")
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "users.info?user=&include_locale=false")
        val verifier = Verifier(response)

        DefaultUsersInfoMethod("", mockTemplate)
                .with(SlackUserInfoRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
