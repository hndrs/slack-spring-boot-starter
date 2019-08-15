package com.kreait.slack.api.spring.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsKickRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelKickResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelKickResponse
import com.kreait.slack.api.contract.jackson.group.channels.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultChannelKickMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("channels.kick Failure")
    fun channelKickFailure() {
        val response = ErrorChannelKickResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.kick", response)
        val verifier = Verifier(response)

        DefaultGetChannelKickMethod("", mockTemplate)
                .with(ChannelsKickRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("channels.kick Success")
    fun channelKickSuccess() {
        val response = SuccessfulChannelKickResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.kick", response)
        val verifier = Verifier(response)

        DefaultGetChannelKickMethod("", mockTemplate)
                .with(ChannelsKickRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
