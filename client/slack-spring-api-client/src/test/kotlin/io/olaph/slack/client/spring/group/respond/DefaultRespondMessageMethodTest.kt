package io.olaph.slack.client.spring.group.respond

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.respond.SlackRespondMessageRequest
import io.olaph.slack.dto.jackson.group.respond.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestTemplate

internal class DefaultRespondMessageMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackResponseTemplate()
    }

    @Test
    @DisplayName("chat.RespondEphemeral Failure")
    fun chatRespondEphemeralFailure() {
        val response = Unit
        val mockServer = MockServerHelper.buildMockRestServerForUrl(mockTemplate, response, "api.slack.com/test", HttpStatus.BAD_REQUEST)
        val verifier = Verifier(response)

        DefaultRespondMessageMethod("api.slack.com/test", mockTemplate)
                .with(SlackRespondMessageRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("chat.RespondEphemeral Success")
    fun chatRespondEphemeralSuccess() {
        val response = Unit
        val mockServer = MockServerHelper.buildMockRestServerForUrl(mockTemplate, response, "api.slack.com/test", HttpStatus.OK)
        val verifier = Verifier(response)

        DefaultRespondMessageMethod("api.slack.com/test", mockTemplate)
                .with(SlackRespondMessageRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
