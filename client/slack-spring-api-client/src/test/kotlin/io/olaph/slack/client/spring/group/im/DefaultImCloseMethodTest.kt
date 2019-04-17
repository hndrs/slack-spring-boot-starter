package io.olaph.slack.client.spring.group.im

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.dto.jackson.group.im.ErrorImCloseResponse
import io.olaph.slack.dto.jackson.group.im.SlackImCloseRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImCloseResponse
import io.olaph.slack.dto.jackson.group.im.sample
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

internal class DefaultImCloseMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplate()
    }

    @Test
    @DisplayName("Im.Close Failure")
    fun ImListFailure() {
        val response = ErrorImCloseResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "im.close")

        DefaultImCloseMethod("", mockTemplate)
                .with(SlackImCloseRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }

    @Test
    @DisplayName("Im.Close Success")
    fun ImListSuccess() {
        val response = SuccessfulImCloseResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "im.close")

        DefaultImCloseMethod("", mockTemplate)
                .with(SlackImCloseRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }
}