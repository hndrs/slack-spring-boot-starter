package io.olaph.slack.client.spring.group.users

import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.users.ErrorUsersSetPhotoResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersSetPhotoResponse
import io.olaph.slack.dto.jackson.group.users.UsersSetPhotoRequest
import io.olaph.slack.dto.jackson.group.users.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "users.setPhoto")
        val verifier = Verifier(response)

        DefaultUsersSetPhotoMethod("", mockTemplate)
                .with(UsersSetPhotoRequest(mock { }, 10, 10, 10))
                .onSuccess { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }

    @DisplayName("users.setPhoto Failure")
    @Test
    fun usersSetPhotoFailure() {

        val response = ErrorUsersSetPhotoResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "users.setPhoto")
        val verifier = Verifier(response)

        DefaultUsersSetPhotoMethod("", mockTemplate)
                .with(UsersSetPhotoRequest(mock { }, 10, 10, 10))
                .onFailure { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }

}