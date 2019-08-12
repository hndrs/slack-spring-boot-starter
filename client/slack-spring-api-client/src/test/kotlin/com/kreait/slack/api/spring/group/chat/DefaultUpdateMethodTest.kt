package com.kreait.slack.api.spring.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatUpdateResponse
import com.kreait.slack.api.contract.jackson.group.chat.SlackChatUpdateRequest
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatUpdateResponse
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
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
