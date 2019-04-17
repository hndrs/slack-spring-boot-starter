package io.olaph.slack.client.spring.group.dialog

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.dto.jackson.group.dialog.ErrorOpenDialogResponse
import io.olaph.slack.dto.jackson.group.dialog.SlackOpenDialogRequest
import io.olaph.slack.dto.jackson.group.dialog.SuccessfulOpenDialogResponse
import io.olaph.slack.dto.jackson.group.dialog.sample
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultDialogOpenMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplate()
    }

    @Test
    @DisplayName("dialog.open Failure")
    fun DialogListFailure() {
        val response = ErrorOpenDialogResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "dialog.open")

        DefaultDialogOpenMethod("", mockTemplate)
                .with(SlackOpenDialogRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }

    @Test
    @DisplayName("dialog.open Success")
    fun DialogListSuccess() {
        val response = SuccessfulOpenDialogResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "dialog.open")

        DefaultDialogOpenMethod("", mockTemplate)
                .with(SlackOpenDialogRequest.sample())
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()
        mockServer.verify()
    }
}