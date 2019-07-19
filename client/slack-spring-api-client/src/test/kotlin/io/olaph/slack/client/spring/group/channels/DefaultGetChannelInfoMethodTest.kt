package io.olaph.slack.client.spring.group.channels

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.channels.ErrorGetChannelInfoResponse
import io.olaph.slack.dto.jackson.group.channels.SlackChannelsInfoRequest
import io.olaph.slack.dto.jackson.group.channels.SuccessfulGetChannelInfoResponse
import io.olaph.slack.dto.jackson.group.channels.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultGetChannelInfoMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("channels.info Failure")
    fun channelInfoFailure() {
        val response = ErrorGetChannelInfoResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.info", response)
        val verifier = Verifier(response)

        DefaultGetChannelInfoMethod("", mockTemplate)
                .with(SlackChannelsInfoRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("channels.info Success")
    fun channelInfoSuccess() {
        val response = SuccessfulGetChannelInfoResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.info", response)
        val verifier = Verifier(response)

        DefaultGetChannelInfoMethod("", mockTemplate)
                .with(SlackChannelsInfoRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
