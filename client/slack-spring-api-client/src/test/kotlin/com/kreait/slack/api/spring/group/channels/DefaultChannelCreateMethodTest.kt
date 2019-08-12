package com.kreait.slack.api.spring.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelCreateResponse
import com.kreait.slack.api.contract.jackson.group.channels.SlackChannelCreateRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelCreateResponse
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultChannelCreateMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("channels.create Failure")
    fun channelCreateFailure() {
        val response = ErrorChannelCreateResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.create", response)
        val verifier = Verifier(response)

        DefaultChannelCreateMethod("", mockTemplate)
                .with(SlackChannelCreateRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("channels.create Success")
    fun channelCreateSuccess() {
        val response = SuccessfulChannelCreateResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.create", response)
        val verifier = Verifier(response)

        DefaultChannelCreateMethod("", mockTemplate)
                .with(SlackChannelCreateRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
