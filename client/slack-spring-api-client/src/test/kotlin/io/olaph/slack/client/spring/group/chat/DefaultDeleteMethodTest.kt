package io.olaph.slack.client.spring.group.chat

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.chat.ErrorChatDeleteResponse
import io.olaph.slack.dto.jackson.group.chat.SlackChatDeleteRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulChatDeleteResponse
import io.olaph.slack.dto.jackson.group.chat.sample
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

internal class DefaultDeleteMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("chat.delete Failure")
    fun chatDeleteFailure() {
        val response = ErrorChatDeleteResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "chat.delete")

        DefaultDeleteMethod("", mockTemplate)
                .with(SlackChatDeleteRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }

    @Test
    @DisplayName("chat.delete Success")
    fun chatDeleteSuccess() {
        val response = SuccessfulChatDeleteResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "chat.delete")

        DefaultDeleteMethod("", mockTemplate)
                .with(SlackChatDeleteRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }
}
