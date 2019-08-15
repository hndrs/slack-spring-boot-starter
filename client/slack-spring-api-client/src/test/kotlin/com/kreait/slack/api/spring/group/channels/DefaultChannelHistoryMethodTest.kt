package com.kreait.slack.api.spring.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsHistoryRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelHistoryResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelHistoryResponse
import com.kreait.slack.api.contract.jackson.group.channels.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultChannelHistoryMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("channels.history Failure")
    fun channelHistoryFailure() {
        val response = ErrorChannelHistoryResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.history", response)
        val verifier = Verifier(response)

        DefaultChannelHistoryMethod("", mockTemplate)
                .with(ChannelsHistoryRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("channels.history Success")
    fun channelHistorySuccess() {
        val response = SuccessfulChannelHistoryResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.history", response)
        val verifier = Verifier(response)

        DefaultChannelHistoryMethod("", mockTemplate)
                .with(ChannelsHistoryRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
