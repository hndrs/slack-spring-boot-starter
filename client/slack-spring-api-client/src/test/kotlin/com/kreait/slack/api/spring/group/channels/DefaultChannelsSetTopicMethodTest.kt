package com.kreait.slack.api.spring.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsSetTopicRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.channels.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultChannelSetTopicMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("channels.setTopic Failure")
    fun channelSetTopicFailure() {
        val response = ErrorChannelsSetTopicResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.setTopic", response)
        val verifier = Verifier(response)

        DefaultChannelSetTopicMethod("", mockTemplate)
                .with(ChannelsSetTopicRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("channels.setTopic Success")
    fun channelSetTopicSuccess() {
        val response = SuccessfulChannelsSetTopicResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.setTopic", response)
        val verifier = Verifier(response)

        DefaultChannelSetTopicMethod("", mockTemplate)
                .with(ChannelsSetTopicRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
