package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.group.users.ErrorUserListResponse
import com.kreait.slack.api.contract.jackson.group.users.SlackUserListRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUserListResponse
import com.kreait.slack.api.spring.group.users.DefaultUserListMethod
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultUserListMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.formUrlTemplate()
    }

    @Test
    @DisplayName("user.list Failure")
    fun UserListFailure() {
        val response = ErrorUserListResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.list", response)
        val verifier = Verifier(response)

        DefaultUserListMethod("", mockTemplate)
                .with(SlackUserListRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("user.list Success")
    fun UserListSuccess() {
        val response = SuccessfulUserListResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.list", response)
        val verifier = Verifier(response)

        DefaultUserListMethod("", mockTemplate)
                .with(SlackUserListRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("user.list Success")
    fun TestWithNextCursor() {
        val response = SuccessfulUserListResponse.sample().copy(responseMetadata = ResponseMetadata("12324"))
        val secondResponse = SuccessfulUserListResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.list", response, secondResponse)
        val verifier = Verifier(response)

        DefaultUserListMethod("", mockTemplate)
                .with(SlackUserListRequest.sample().copy(limit = 10, cursor = "12324"))
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

}
