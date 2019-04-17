package io.olaph.slack.client.spring.group.im

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.dto.jackson.group.im.ErrorImMarkResponse
import io.olaph.slack.dto.jackson.group.im.SlackImMarkRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImMarkResponse
import io.olaph.slack.dto.jackson.group.im.sample
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

internal class DefaultImMarkMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplate()
    }

    @Test
    @DisplayName("Im.Mark Failure")
    fun ImListFailure() {
        val response = ErrorImMarkResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "im.mark")

        DefaultImMarkMethod("", mockTemplate)
                .with(SlackImMarkRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }

    @Test
    @DisplayName("Im.Mark Success")
    fun ImListSuccess() {
        val response = SuccessfulImMarkResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "im.mark")

        DefaultImMarkMethod("", mockTemplate)
                .with(SlackImMarkRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }
}