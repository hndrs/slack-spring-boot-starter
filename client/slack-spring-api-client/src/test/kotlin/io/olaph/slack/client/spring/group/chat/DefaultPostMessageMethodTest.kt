package io.olaph.slack.client.spring.group.chat

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.dto.jackson.group.chat.ErrorPostMessageResponse
import io.olaph.slack.dto.jackson.group.chat.SlackPostMessageRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulPostMessageResponse
import io.olaph.slack.dto.jackson.group.chat.sample
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultPostMessageMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplate()
    }

    @Test
    @DisplayName("chat.postMessage Failure")
    fun chatPostMessageFailure() {
        val response = ErrorPostMessageResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "chat.postMessage")

        DefaultPostMessageMethod("", mockTemplate)
                .with(SlackPostMessageRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }

    @Test
    @DisplayName("chat.PostMessage Success")
    fun chatPostMessageSuccess() {
        val response = SuccessfulPostMessageResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "chat.postMessage")

        DefaultPostMessageMethod("", mockTemplate)
                .with(SlackPostMessageRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }
}