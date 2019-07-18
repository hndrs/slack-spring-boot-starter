package io.olaph.slack.client.spring.group.users

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.users.ErrorUserListResponse
import io.olaph.slack.dto.jackson.group.users.SlackUserListRequest
import io.olaph.slack.dto.jackson.group.users.SuccessfulUserListResponse
import io.olaph.slack.dto.jackson.group.users.sample
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "users.list")
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "users.list")
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
        val response = SuccessfulUserListResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "users.list")
        val verifier = Verifier(response)

        DefaultUserListMethod("", mockTemplate)
                .with(SlackUserListRequest.sample().copy(limit = 10, cursor = "12324"))
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

}
