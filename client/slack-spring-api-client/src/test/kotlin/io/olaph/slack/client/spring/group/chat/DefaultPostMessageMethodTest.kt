package io.olaph.slack.client.spring.group.chat

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.chat.ErrorPostMessageResponse
import io.olaph.slack.dto.jackson.group.chat.SlackPostMessageRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulPostMessageResponse
import io.olaph.slack.dto.jackson.group.chat.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultPostMessageMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("chat.postMessage Failure")
    fun chatPostMessageFailure() {
        val response = ErrorPostMessageResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "chat.postMessage")
        val verifier = Verifier(response)

        DefaultPostMessageMethod("", mockTemplate)
                .with(SlackPostMessageRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("chat.PostMessage Success")
    fun chatPostMessageSuccess() {
        val response = SuccessfulPostMessageResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "chat.postMessage")
        val verifier = Verifier(response)

        DefaultPostMessageMethod("", mockTemplate)
                .with(SlackPostMessageRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
