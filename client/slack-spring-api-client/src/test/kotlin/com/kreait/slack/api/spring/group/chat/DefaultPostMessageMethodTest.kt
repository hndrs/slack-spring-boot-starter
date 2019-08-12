package com.kreait.slack.api.spring.group.chat

import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.contract.jackson.group.chat.ErrorPostMessageResponse
import com.kreait.slack.api.contract.jackson.group.chat.SlackPostMessageRequest
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulPostMessageResponse
import com.kreait.slack.api.spring.group.chat.DefaultPostMessageMethod
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "chat.postMessage", response)
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "chat.postMessage", response)
        val verifier = Verifier(response)

        DefaultPostMessageMethod("", mockTemplate)
                .with(SlackPostMessageRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
