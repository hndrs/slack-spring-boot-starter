package io.olaph.slack.client.spring.group.channels

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.channels.ErrorChannelInviteResponse
import io.olaph.slack.dto.jackson.group.channels.SlackChannelInviteRequest
import io.olaph.slack.dto.jackson.group.channels.SuccessfulChannelInviteResponse
import io.olaph.slack.dto.jackson.group.channels.sample
import org.junit.jupiter.api.Assertions
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "channels.invite")

        DefaultChannelInviteMethod("", mockTemplate)
                .with(SlackChannelInviteRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }

    @Test
    @DisplayName("channels.invite Success")
    fun channelInviteSuccess() {
        val response = SuccessfulChannelInviteResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "channels.invite")

        DefaultChannelInviteMethod("", mockTemplate)
                .with(SlackChannelInviteRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }
}
