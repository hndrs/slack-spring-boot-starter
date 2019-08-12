package com.kreait.slack.api.spring.group.im

import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.contract.jackson.group.im.ErrorImRepliesResponse
import com.kreait.slack.api.contract.jackson.group.im.SlackImRepliesRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImRepliesResponse
import com.kreait.slack.api.contract.jackson.group.im.sample
import com.kreait.slack.api.spring.group.im.DefaultImRepliesMethod
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

internal class DefaultImRepliesMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("Im.replies Failure")
    fun imRepliesFailure() {
        val response = ErrorImRepliesResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "im.replies", response)
        val verifier = Verifier(response)

        DefaultImRepliesMethod("", mockTemplate)
                .with(SlackImRepliesRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("Im.replies Success")
    fun imRepliesSuccess() {
        val response = SuccessfulImRepliesResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "im.replies", response)
        val verifier = Verifier(response)

        DefaultImRepliesMethod("", mockTemplate)
                .with(SlackImRepliesRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
