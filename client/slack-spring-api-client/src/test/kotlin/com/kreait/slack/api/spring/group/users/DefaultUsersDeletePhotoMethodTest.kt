package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersDeletePhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersDeletePhotoResponse
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.deletePhoto", response)
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.deletePhoto", response)
        val verifier = Verifier(response)

        DefaultUsersDeletePhotoMethod("", mockTemplate)
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
