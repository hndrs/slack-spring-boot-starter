package com.kreait.slack.api.spring.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ErrorPostEphemeralResponse
import com.kreait.slack.api.contract.jackson.group.chat.SlackPostEphemeralRequest
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulPostEphemeralResponse
import com.kreait.slack.api.contract.jackson.group.chat.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

internal class DefaultPostEphemeralMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("chat.PostEphemeral Failure")
    fun chatPostEphemeralFailure() {
        val response = ErrorPostEphemeralResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "chat.postEphemeral", response)
        val verifier = Verifier(response)

        DefaultPostEphemeralMethod("", mockTemplate)
                .with(SlackPostEphemeralRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("chat.PostEphemeral Success")
    fun chatPostEphemeralSuccess() {
        val response = SuccessfulPostEphemeralResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "chat.postEphemeral", response)
        val verifier = Verifier(response)

        DefaultPostEphemeralMethod("", mockTemplate)
                .with(SlackPostEphemeralRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
