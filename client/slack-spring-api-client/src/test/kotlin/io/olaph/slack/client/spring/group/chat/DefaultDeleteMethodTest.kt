package io.olaph.slack.client.spring.group.chat

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.chat.ErrorChatDeleteResponse
import io.olaph.slack.dto.jackson.group.chat.SlackChatDeleteRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulChatDeleteResponse
import io.olaph.slack.dto.jackson.group.chat.sample
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "chat.delete", response)
        val verifier = Verifier(response)

        DefaultDeleteMethod("", mockTemplate)
                .with(SlackChatDeleteRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("chat.delete Success")
    fun chatDeleteSuccess() {
        val response = SuccessfulChatDeleteResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "chat.delete", response)
        val verifier = Verifier(response)

        DefaultDeleteMethod("", mockTemplate)
                .with(SlackChatDeleteRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
