package com.kreait.slack.api.spring.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsMarkRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsMarkResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsMarkResponse
import com.kreait.slack.api.contract.jackson.group.channels.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultChannelMarkMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("channels.mark Failure")
    fun channelMarkFailure() {
        val response = ErrorChannelsMarkResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.mark", response)
        val verifier = Verifier(response)

        DefaultChannelMarkMethod("", mockTemplate)
                .with(ChannelsMarkRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("channels.mark Success")
    fun channelMarkSuccess() {
        val response = SuccessfulChannelsMarkResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.mark", response)
        val verifier = Verifier(response)

        DefaultChannelMarkMethod("", mockTemplate)
                .with(ChannelsMarkRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
