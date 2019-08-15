package com.kreait.slack.api.spring.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsLeaveRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsLeaveResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsLeaveResponse
import com.kreait.slack.api.contract.jackson.group.channels.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultChannelsLeaveMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("channels.leave Failure")
    fun channelLeaveFailure() {
        val response = ErrorChannelsLeaveResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.leave", response)
        val verifier = Verifier(response)

        DefaultChannelsLeaveMethod("", mockTemplate)
                .with(ChannelsLeaveRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("channels.leave Success")
    fun channelLeaveSuccess() {
        val response = SuccessfulChannelsLeaveResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "channels.leave", response)
        val verifier = Verifier(response)

        DefaultChannelsLeaveMethod("", mockTemplate)
                .with(ChannelsLeaveRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
