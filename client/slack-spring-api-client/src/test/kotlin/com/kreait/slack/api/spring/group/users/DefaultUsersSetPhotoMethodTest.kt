package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersSetPhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersSetPhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.UsersSetPhotoRequest
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.core.io.ClassPathResource
import org.springframework.web.client.RestTemplate

class DefaultUsersSetPhotoMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @DisplayName("users.setPhoto Success")
    @Test
    fun usersSetPhotoSuccess() {

        val response = SuccessfulUsersSetPhotoResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.setPhoto", response)
        val verifier = Verifier(response)

        DefaultUsersSetPhotoMethod("", mockTemplate)
                .with(UsersSetPhotoRequest(ClassPathResource("olaph.png").file, 10, 10, 10))
                .onSuccess { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }

    @DisplayName("users.setPhoto Failure")
    @Test
    fun usersSetPhotoFailure() {

        val response = ErrorUsersSetPhotoResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.setPhoto", response)
        val verifier = Verifier(response)

        DefaultUsersSetPhotoMethod("", mockTemplate)
                .with(UsersSetPhotoRequest(ClassPathResource("olaph.png").file, 10, 10, 10))
                .onFailure { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }

}
