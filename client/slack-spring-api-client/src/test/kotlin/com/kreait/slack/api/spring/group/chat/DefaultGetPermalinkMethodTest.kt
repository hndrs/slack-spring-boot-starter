package com.kreait.slack.api.spring.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ChatGetPermalinkRequest
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatGetPermalinkResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatGetPermalinkResponse
import com.kreait.slack.api.contract.jackson.group.chat.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultGetPermalinkMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("chat.getPermalink Failure")
    fun chatGetPermalinkFailure() {
        val response = ErrorChatGetPermalinkResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "chat.getPermalink", response)
        val verifier = Verifier(response)

        DefaultGetPermalinkMethod("", mockTemplate)
                .with(ChatGetPermalinkRequest.sample())
                .onFailure { verifier.set(it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("chat.getPermalink Success")
    fun chatGetPermalinkSuccess() {
        val response = SuccessfulChatGetPermalinkResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "chat.getPermalink", response)
        val verifier = Verifier(response)

        DefaultGetPermalinkMethod("", mockTemplate)
                .with(ChatGetPermalinkRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
