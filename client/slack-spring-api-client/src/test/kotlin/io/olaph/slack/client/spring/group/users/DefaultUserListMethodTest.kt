package io.olaph.slack.client.spring.group.users

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.dto.jackson.group.users.ErrorUserListResponse
import io.olaph.slack.dto.jackson.group.users.SlackUserListRequest
import io.olaph.slack.dto.jackson.group.users.SuccessfulUserListResponse
import io.olaph.slack.dto.jackson.group.users.sample
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultUserListMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplate()
    }

    @Test
    @DisplayName("user.list Failure")
    fun UserListFailure() {
        val response = ErrorUserListResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "users.list?cursor=&include_locale=false&limit=0&presence=false")

        DefaultUserListMethod("", mockTemplate)
                .with(SlackUserListRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }

    @Test
    @DisplayName("user.list Success")
    fun UserListSuccess() {
        val response = SuccessfulUserListResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "users.list?cursor=&include_locale=false&limit=0&presence=false")

        DefaultUserListMethod("", mockTemplate)
                .with(SlackUserListRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }

}