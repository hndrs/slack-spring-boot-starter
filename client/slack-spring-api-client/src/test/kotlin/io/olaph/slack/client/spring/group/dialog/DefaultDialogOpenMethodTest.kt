package io.olaph.slack.client.spring.group.dialog

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.dialog.ErrorOpenDialogResponse
import io.olaph.slack.dto.jackson.group.dialog.SlackOpenDialogRequest
import io.olaph.slack.dto.jackson.group.dialog.SuccessfulOpenDialogResponse
import io.olaph.slack.dto.jackson.group.dialog.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultDialogOpenMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("dialog.open Failure")
    fun DialogListFailure() {
        val response = ErrorOpenDialogResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "dialog.open")
        val verifier = Verifier(response)

        DefaultDialogOpenMethod("", mockTemplate)
                .with(SlackOpenDialogRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("dialog.open Success")
    fun DialogListSuccess() {
        val response = SuccessfulOpenDialogResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "dialog.open")
        val verifier = Verifier(response)

        DefaultDialogOpenMethod("", mockTemplate)
                .with(SlackOpenDialogRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
