package com.kreait.slack.api.spring.group.reminders

import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersAddResponse
import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersCompleteResponse
import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersDeleteResponse
import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersInfoResponse
import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersListResponse
import com.kreait.slack.api.contract.jackson.group.reminders.RemindersDeleteRequest
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersAddResponse
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersCompleteResponse
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersDeleteResponse
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersInfoResponse
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersListResponse
import com.kreait.slack.api.contract.jackson.group.reminders.sample
import com.kreait.slack.api.spring.DynamicGroupTests
import com.kreait.slack.api.spring.MetaInfo
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.springframework.web.client.RestTemplate

internal class DefaultReminderGroupTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @TestFactory
    fun methodInvocations(): List<DynamicTest> = DynamicGroupTests.methodInvocations(testCases = testCases(), mockTemplate = mockTemplate)

    private fun testCases() = listOf(
            MetaInfo("reminders.delete", SuccessfulRemindersDeleteResponse.sample(), ErrorRemindersDeleteResponse.sample(), RemindersDeleteRequest.sample(), DefaultRemindersDeleteMethod("", mockTemplate)),
            MetaInfo("reminders.list", SuccessfulRemindersListResponse.sample(), ErrorRemindersListResponse.sample(), Unit, DefaultRemindersListMethod("", mockTemplate)),
            MetaInfo("reminders.info", SuccessfulRemindersInfoResponse.sample(), ErrorRemindersInfoResponse.sample(), Unit, DefaultRemindersInfoMethod("", mockTemplate)),
            MetaInfo("reminders.complete", SuccessfulRemindersCompleteResponse.sample(), ErrorRemindersCompleteResponse.sample(), Unit, DefaultRemindersCompleteMethod("", mockTemplate)),
            MetaInfo("reminders.add", SuccessfulRemindersAddResponse.sample(), ErrorRemindersAddResponse.sample(), Unit, DefaultRemindersAddMethod("", mockTemplate))
    )
}
