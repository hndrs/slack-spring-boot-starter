package com.kreait.slack.api.spring.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelMarkRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelMarkResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelMarkResponse
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
        val response = ErrorChannelMarkResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.mark", response)
        val verifier = Verifier(response)

        DefaultChannelMarkMethod("", mockTemplate)
                .with(ChannelMarkRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("channels.mark Success")
    fun channelMarkSuccess() {
        val response = SuccessfulChannelMarkResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.mark", response)
        val verifier = Verifier(response)

        DefaultChannelMarkMethod("", mockTemplate)
                .with(ChannelMarkRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
