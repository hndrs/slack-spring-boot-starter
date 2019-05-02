package io.olaph.slack.client.spring.group.users

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.users.ErrorUsersDeletePhotoResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersDeletePhotoResponse
import io.olaph.slack.dto.jackson.group.users.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultUsersDeletePhotoMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("Users.deletePhoto Failure")
    fun UserDeletePhotoFailure() {
        val response = ErrorUsersDeletePhotoResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "users.deletePhoto")
        val verifier = Verifier(response)

        DefaultUsersDeletePhotoMethod("", mockTemplate)
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("Users.deletePhoto Success")
    fun UserDeletePhotoSuccess() {
        val response = SuccessfulUsersDeletePhotoResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "users.deletePhoto")
        val verifier = Verifier(response)

        DefaultUsersDeletePhotoMethod("", mockTemplate)
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
