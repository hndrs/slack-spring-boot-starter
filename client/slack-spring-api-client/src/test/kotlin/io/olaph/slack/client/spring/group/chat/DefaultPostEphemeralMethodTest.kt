package io.olaph.slack.client.spring.group.chat

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.dto.jackson.group.chat.ErrorPostEphemeralResponse
import io.olaph.slack.dto.jackson.group.chat.SlackPostEphemeralRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulPostEphemeralResponse
import io.olaph.slack.dto.jackson.group.chat.sample
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

internal class DefaultPostEphemeralMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplate()
    }

    @Test
    @DisplayName("chat.PostEphemeral Failure")
    fun chatPostEphemeralFailure() {
        val response = ErrorPostEphemeralResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "chat.postEphemeral")

        DefaultPostEphemeralMethod("", mockTemplate)
                .with(SlackPostEphemeralRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }

    @Test
    @DisplayName("chat.PostEphemeral Success")
    fun chatPostEphemeralSuccess() {
        val response = SuccessfulPostEphemeralResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "chat.postEphemeral")

        DefaultPostEphemeralMethod("", mockTemplate)
                .with(SlackPostEphemeralRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }
}