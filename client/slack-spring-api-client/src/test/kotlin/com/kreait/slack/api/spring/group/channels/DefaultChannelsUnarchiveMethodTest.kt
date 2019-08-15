package com.kreait.slack.api.spring.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsUnarchiveRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.channels.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultChannelsUnarchiveMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("channels.unarchive Failure")
    fun channelUnarchiveFailure() {
        val response = ErrorChannelUnarchiveResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.unarchive", response)
        val verifier = Verifier(response)

        DefaultChannelsUnarchiveMethod("", mockTemplate)
                .with(ChannelsUnarchiveRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
    }

    @Test
    @DisplayName("channels.unarchive Success")
    fun channelUnarchiveSuccess() {
        val response = SuccessfulChannelUnarchiveResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.unarchive", response)
        val verifier = Verifier(response)

        DefaultChannelsUnarchiveMethod("", mockTemplate)
                .with(ChannelsUnarchiveRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
