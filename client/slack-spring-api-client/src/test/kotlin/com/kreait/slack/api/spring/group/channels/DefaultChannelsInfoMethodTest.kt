package com.kreait.slack.api.spring.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelInfoResponse
import com.kreait.slack.api.contract.jackson.group.channels.ChannelsInfoRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelInfoResponse
import com.kreait.slack.api.contract.jackson.group.channels.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultChannelsInfoMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("channels.info Failure")
    fun channelInfoFailure() {
        val response = ErrorChannelInfoResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.info", response)
        val verifier = Verifier(response)

        DefaultChannelsInfoMethod("", mockTemplate)
                .with(ChannelsInfoRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("channels.info Success")
    fun channelInfoSuccess() {
        val response = SuccessfulChannelInfoResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.info", response)
        val verifier = Verifier(response)

        DefaultChannelsInfoMethod("", mockTemplate)
                .with(ChannelsInfoRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
