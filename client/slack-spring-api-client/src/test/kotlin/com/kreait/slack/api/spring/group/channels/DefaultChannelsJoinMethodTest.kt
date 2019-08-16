package com.kreait.slack.api.spring.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsJoinRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsJoinResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsJoinResponse
import com.kreait.slack.api.contract.jackson.group.channels.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultChannelsJoinMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("channels.join Failure")
    fun channelJoinFailure() {
        val response = ErrorChannelsJoinResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.join", response)
        val verifier = Verifier(response)

        DefaultChannelsJoinMethod("", mockTemplate)
                .with(ChannelsJoinRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("channels.join Success")
    fun channelJoinSuccess() {
        val response = SuccessfulChannelsJoinResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.join", response)
        val verifier = Verifier(response)

        DefaultChannelsJoinMethod("", mockTemplate)
                .with(ChannelsJoinRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
