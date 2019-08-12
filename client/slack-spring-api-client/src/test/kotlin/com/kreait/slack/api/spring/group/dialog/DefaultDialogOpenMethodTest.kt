package com.kreait.slack.api.spring.group.dialog

import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.contract.jackson.group.dialog.ErrorOpenDialogResponse
import com.kreait.slack.api.contract.jackson.group.dialog.SlackOpenDialogRequest
import com.kreait.slack.api.contract.jackson.group.dialog.SuccessfulOpenDialogResponse
import com.kreait.slack.api.contract.jackson.group.dialog.sample
import com.kreait.slack.api.spring.group.dialog.DefaultDialogOpenMethod
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "dialog.open", response)
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "dialog.open", response)
        val verifier = Verifier(response)

        DefaultDialogOpenMethod("", mockTemplate)
                .with(SlackOpenDialogRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
