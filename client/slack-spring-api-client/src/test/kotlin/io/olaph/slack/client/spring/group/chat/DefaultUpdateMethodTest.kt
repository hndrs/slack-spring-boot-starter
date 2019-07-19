package io.olaph.slack.client.spring.group.chat

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.chat.ErrorChatUpdateResponse
import io.olaph.slack.dto.jackson.group.chat.SlackChatUpdateRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulChatUpdateResponse
import io.olaph.slack.dto.jackson.group.chat.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultUpdateMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("chat.update Failure")
    fun chatUpdateFailure() {
        val response = ErrorChatUpdateResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "chat.update", response)
        val verifier = Verifier(response)

        DefaultUpdateMethod("", mockTemplate)
                .with(SlackChatUpdateRequest.sample())
                .onFailure { verifier.set(it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("chat.update Success")
    fun chatUpdateSuccess() {
        val response = SuccessfulChatUpdateResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "chat.update", response)
        val verifier = Verifier(response)

        DefaultUpdateMethod("", mockTemplate)
                .with(SlackChatUpdateRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
