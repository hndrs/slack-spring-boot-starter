package com.kreait.slack.api.spring.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelRenameRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelRenameResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelRenameResponse
import com.kreait.slack.api.contract.jackson.group.channels.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultChannelsRenameMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("channels.rename Failure")
    fun channelRenameFailure() {
        val response = ErrorChannelRenameResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.rename", response)
        val verifier = Verifier(response)

        DefaultChannelsRenameMethod("", mockTemplate)
                .with(ChannelRenameRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("channels.rename Success")
    fun channelRenameSuccess() {
        val response = SuccessfulChannelRenameResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.rename", response)
        val verifier = Verifier(response)

        DefaultChannelsRenameMethod("", mockTemplate)
                .with(ChannelRenameRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
