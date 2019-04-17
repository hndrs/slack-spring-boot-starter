package io.olaph.slack.client.spring.group.users

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.users.ErrorUserConversationsResponse
import io.olaph.slack.dto.jackson.group.users.SlackUserConversationListRequest
import io.olaph.slack.dto.jackson.group.users.SuccessfulUserConversationsResponse
import io.olaph.slack.dto.jackson.group.users.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultUserConversationsMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("Users.conversations Failure")
    fun UserConversationsFailure() {
        val response = ErrorUserConversationsResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "users.conversations?cursor=&exclude_archived=true&limit=0&types=im&user=")
        val verifier = Verifier(response)

        DefaultUserConversationsMethod("", mockTemplate)
                .with(SlackUserConversationListRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("Users.conversations Success")
    fun UserConversationsSuccess() {
        val response = SuccessfulUserConversationsResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "users.conversations?cursor=&exclude_archived=true&limit=0&types=im&user=")
        val verifier = Verifier(response)

        DefaultUserConversationsMethod("", mockTemplate)
                .with(SlackUserConversationListRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
