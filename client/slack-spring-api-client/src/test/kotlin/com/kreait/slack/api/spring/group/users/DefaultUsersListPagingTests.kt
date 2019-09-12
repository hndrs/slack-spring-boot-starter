package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.group.users.ErrorListResponse
import com.kreait.slack.api.contract.jackson.group.users.ListRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulListResponse
import com.kreait.slack.api.contract.jackson.group.users.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate


class DefaultUsersListPagingTests {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("user.list Failure")
    fun UserListFailure() {
        val response = ErrorListResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.list", response)
        val verifier = Verifier(response)

        DefaultUserListMethodPaging("", mockTemplate)
                .with(ListRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("user.list Success")
    fun UserListSuccess() {
        val response = SuccessfulListResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.list", response)
        val verifier = Verifier(response)

        DefaultUserListMethodPaging("", mockTemplate)
                .with(ListRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("user.list Success")
    fun TestWithNextCursor() {
        val response = SuccessfulListResponse.sample().copy(responseMetadata = ResponseMetadata("12324"))
        val secondResponse = SuccessfulListResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.list", response, secondResponse)
        val verifier = Verifier(response)

        DefaultUserListMethodPaging("", mockTemplate)
                .with(ListRequest.sample().copy(limit = 10, cursor = "12324"))
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

}
