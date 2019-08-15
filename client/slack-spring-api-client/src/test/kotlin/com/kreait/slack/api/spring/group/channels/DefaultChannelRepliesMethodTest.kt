package com.kreait.slack.api.spring.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelRepliesResponse
import com.kreait.slack.api.contract.jackson.group.channels.ChannelRepliesRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelRepliesResponse
import com.kreait.slack.api.contract.jackson.group.channels.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultChannelRepliesMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("channels.replies Failure")
    fun channelRepliesFailure() {
        val response = ErrorChannelRepliesResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.replies", response)
        val verifier = Verifier(response)

        DefaultChannelRepliesMethod("", mockTemplate)
                .with(ChannelRepliesRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("channels.replies Success")
    fun channelRepliesSuccess() {
        val response = SuccessfulChannelRepliesResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.replies", response)
        val verifier = Verifier(response)

        DefaultChannelRepliesMethod("", mockTemplate)
                .with(ChannelRepliesRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
