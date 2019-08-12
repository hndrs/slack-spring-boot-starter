package com.kreait.slack.api.spring.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelInviteResponse
import com.kreait.slack.api.contract.jackson.group.channels.SlackChannelInviteRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelInviteResponse
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultChannelInviteMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("channels.invite Failure")
    fun channelInviteFailure() {
        val response = ErrorChannelInviteResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.invite", response)
        val verifier = Verifier(response)

        DefaultChannelInviteMethod("", mockTemplate)
                .with(SlackChannelInviteRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("channels.invite Success")
    fun channelInviteSuccess() {
        val response = SuccessfulChannelInviteResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.invite", response)
        val verifier = Verifier(response)

        DefaultChannelInviteMethod("", mockTemplate)
                .with(SlackChannelInviteRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
