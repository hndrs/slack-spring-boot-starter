package io.olaph.slack.client.spring.group.channels

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.channels.ErrorChannelCreateResponse
import io.olaph.slack.dto.jackson.group.channels.SlackChannelCreateRequest
import io.olaph.slack.dto.jackson.group.channels.SuccessfulChannelCreateResponse
import io.olaph.slack.dto.jackson.group.channels.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultChannelCreateMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("channels.create Failure")
    fun channelCreateFailure() {
        val response = ErrorChannelCreateResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "channels.create")
        val verifier = Verifier(response)

        DefaultChannelCreateMethod("", mockTemplate)
                .with(SlackChannelCreateRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("channels.create Success")
    fun channelCreateSuccess() {
        val response = SuccessfulChannelCreateResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "channels.create")
        val verifier = Verifier(response)

        DefaultChannelCreateMethod("", mockTemplate)
                .with(SlackChannelCreateRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
