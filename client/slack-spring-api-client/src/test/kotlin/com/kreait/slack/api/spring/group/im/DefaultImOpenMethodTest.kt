package com.kreait.slack.api.spring.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImOpenResponse
import com.kreait.slack.api.contract.jackson.group.im.ImOpenRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImOpenResponse
import com.kreait.slack.api.contract.jackson.group.im.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

internal class DefaultImOpenMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("Im.Open Failure")
    fun imOpenFailure() {
        val response = ErrorImOpenResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "im.open", response)
        val verifier = Verifier(response)

        DefaultImOpenMethod("", mockTemplate)
                .with(ImOpenRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("Im.Open Success")
    fun imOpenSuccess() {
        val response = SuccessfulImOpenResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "im.open", response)
        val verifier = Verifier(response)

        DefaultImOpenMethod("", mockTemplate)
                .with(ImOpenRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
