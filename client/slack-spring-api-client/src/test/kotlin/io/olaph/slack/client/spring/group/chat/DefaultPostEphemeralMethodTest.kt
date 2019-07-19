package io.olaph.slack.client.spring.group.chat

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.chat.ErrorPostEphemeralResponse
import io.olaph.slack.dto.jackson.group.chat.SlackPostEphemeralRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulPostEphemeralResponse
import io.olaph.slack.dto.jackson.group.chat.sample
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
