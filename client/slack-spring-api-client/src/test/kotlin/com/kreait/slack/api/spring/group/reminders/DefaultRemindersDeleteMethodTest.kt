package com.kreait.slack.api.spring.group.reminders

import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersDeleteResponse
import com.kreait.slack.api.contract.jackson.group.reminders.RemindersDeleteRequest
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersDeleteResponse
import com.kreait.slack.api.contract.jackson.group.reminders.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.reminders.DefaultRemindersDeleteMethod
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

internal class DefaultRemindersDeleteMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("reminders.delete Failure")
    fun remindersDeleteFailure() {
        val response = ErrorRemindersDeleteResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "reminders.delete", response)
        val verifier = Verifier(response)

        DefaultRemindersDeleteMethod("", mockTemplate)
                .with(RemindersDeleteRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("reminders.delete Success")
    fun remindersDeleteSuccess() {
        val response = SuccessfulRemindersDeleteResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "reminders.delete", response)
        val verifier = Verifier(response)

        DefaultRemindersDeleteMethod("", mockTemplate)
                .with(RemindersDeleteRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
