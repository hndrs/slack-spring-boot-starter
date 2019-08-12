package com.kreait.slack.api.spring.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelArchiveResponse
import com.kreait.slack.api.contract.jackson.group.channels.ChannelsArchiveRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelArchiveResponse
import com.kreait.slack.api.contract.jackson.group.channels.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.archive", response)
        val verifier = Verifier(response)

        DefaultChannelsArchiveMethod("", mockTemplate)
                .with(ChannelsArchiveRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
    }

    @Test
    @DisplayName("channels.archive Success")
    fun channelArchiveSuccess() {
        val response = SuccessfulChannelArchiveResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.archive", response)
        val verifier = Verifier(response)

        DefaultChannelsArchiveMethod("", mockTemplate)
                .with(ChannelsArchiveRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
