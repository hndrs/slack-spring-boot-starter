package io.olaph.slack.client.spring.group.channels

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.channels.ErrorChannelArchiveResponse
import io.olaph.slack.dto.jackson.group.channels.SlackChannelsArchiveRequest
import io.olaph.slack.dto.jackson.group.channels.SuccessfulChannelArchiveResponse
import io.olaph.slack.dto.jackson.group.channels.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultChannelsArchiveMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("channels.archive Failure")
    fun channelArchiveFailure() {
        val response = ErrorChannelArchiveResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "channels.archive")
        val verifier = Verifier(response)

        DefaultChannelsArchiveMethod("", mockTemplate)
                .with(SlackChannelsArchiveRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
    }

    @Test
    @DisplayName("channels.archive Success")
    fun channelArchiveSuccess() {
        val response = SuccessfulChannelArchiveResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "channels.archive")
        val verifier = Verifier(response)

        DefaultChannelsArchiveMethod("", mockTemplate)
                .with(SlackChannelsArchiveRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
