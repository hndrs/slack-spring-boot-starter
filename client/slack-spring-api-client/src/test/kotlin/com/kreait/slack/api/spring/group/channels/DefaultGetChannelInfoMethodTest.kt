package com.kreait.slack.api.spring.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ErrorGetChannelInfoResponse
import com.kreait.slack.api.contract.jackson.group.channels.SlackChannelsInfoRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulGetChannelInfoResponse
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultGetChannelInfoMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("channels.info Failure")
    fun channelInfoFailure() {
        val response = ErrorGetChannelInfoResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.info", response)
        val verifier = Verifier(response)

        DefaultGetChannelInfoMethod("", mockTemplate)
                .with(SlackChannelsInfoRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("channels.info Success")
    fun channelInfoSuccess() {
        val response = SuccessfulGetChannelInfoResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.info", response)
        val verifier = Verifier(response)

        DefaultGetChannelInfoMethod("", mockTemplate)
                .with(SlackChannelsInfoRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
