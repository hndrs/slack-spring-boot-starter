package com.kreait.slack.api.spring.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelSetPurposeRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.channels.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultChannelSetPurposeMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("channels.setPurpose Failure")
    fun channelSetPurposeFailure() {
        val response = ErrorChannelSetPurposeResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.setPurpose", response)
        val verifier = Verifier(response)

        DefaultChannelSetPurposeMethod("", mockTemplate)
                .with(ChannelSetPurposeRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("channels.setPurpose Success")
    fun channelSetPurposeSuccess() {
        val response = SuccessfulChannelSetPurposeResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.setPurpose", response)
        val verifier = Verifier(response)

        DefaultChannelSetPurposeMethod("", mockTemplate)
                .with(ChannelSetPurposeRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
