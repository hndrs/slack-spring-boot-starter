package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorSetPhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulSetPhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SetPhotoRequest
import com.kreait.slack.api.contract.jackson.group.users.sample
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

        val response = SuccessfulSetPhotoResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.setPhoto", response)
        val verifier = Verifier(response)

        DefaultUsersSetPhotoMethod("", mockTemplate)
                .with(SetPhotoRequest(ClassPathResource("olaph.png").file, 10, 10, 10))
                .onSuccess { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }

    @DisplayName("users.setPhoto Failure")
    @Test
    fun usersSetPhotoFailure() {

        val response = ErrorSetPhotoResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.setPhoto", response)
        val verifier = Verifier(response)

        DefaultUsersSetPhotoMethod("", mockTemplate)
                .with(SetPhotoRequest(ClassPathResource("olaph.png").file, 10, 10, 10))
                .onFailure { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }

}
