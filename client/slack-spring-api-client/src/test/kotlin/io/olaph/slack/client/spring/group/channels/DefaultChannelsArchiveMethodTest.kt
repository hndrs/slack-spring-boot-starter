package io.olaph.slack.client.spring.group.channels

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.channels.ErrorChannelArchiveResponse
import io.olaph.slack.dto.jackson.group.channels.SlackChannelsArchiveRequest
import io.olaph.slack.dto.jackson.group.channels.SuccessfulChannelArchiveResponse
import io.olaph.slack.dto.jackson.group.channels.sample
import org.junit.jupiter.api.Assertions
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

        DefaultChannelsArchiveMethod("", mockTemplate)
                .with(SlackChannelsArchiveRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }

    @Test
    @DisplayName("channels.archive Success")
    fun channelArchiveSuccess() {
        val response = SuccessfulChannelArchiveResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "channels.archive")

        DefaultChannelsArchiveMethod("", mockTemplate)
                .with(SlackChannelsArchiveRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }
}
