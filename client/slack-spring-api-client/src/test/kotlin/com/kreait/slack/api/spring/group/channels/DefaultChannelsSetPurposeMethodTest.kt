package com.kreait.slack.api.spring.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsSetPurposeRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.channels.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultChannelsSetPurposeMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("channels.setPurpose Failure")
    fun channelSetPurposeFailure() {
        val response = ErrorChannelsSetPurposeResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.setPurpose", response)
        val verifier = Verifier(response)

        DefaultChannelsSetPurposeMethod("", mockTemplate)
                .with(ChannelsSetPurposeRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("channels.setPurpose Success")
    fun channelSetPurposeSuccess() {
        val response = SuccessfulChannelsSetPurposeResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.setPurpose", response)
        val verifier = Verifier(response)

        DefaultChannelsSetPurposeMethod("", mockTemplate)
                .with(ChannelsSetPurposeRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
