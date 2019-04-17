package io.olaph.slack.client.spring.group.im

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.dto.jackson.group.im.ErrorImOpenResponse
import io.olaph.slack.dto.jackson.group.im.SlackImOpenRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImOpenResponse
import io.olaph.slack.dto.jackson.group.im.sample
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

internal class DefaultImOpenMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplate()
    }

    @Test
    @DisplayName("Im.Open Failure")
    fun imOpenFailure() {
        val response = ErrorImOpenResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "im.open")

        DefaultImOpenMethod("", mockTemplate)
                .with(SlackImOpenRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }

    @Test
    @DisplayName("Im.Open Success")
    fun imOpenSuccess() {
        val response = SuccessfulImOpenResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "im.open")

        DefaultImOpenMethod("", mockTemplate)
                .with(SlackImOpenRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }
}