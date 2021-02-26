package io.hndrs.slack.api.spring.group.reminders

import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersAddResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersCompleteResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersDeleteResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersInfoResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersListResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.RemindersDeleteRequest
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersAddResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersCompleteResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersDeleteResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersInfoResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersListResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.sample
import io.hndrs.slack.api.spring.DynamicGroupTests
import io.hndrs.slack.api.spring.MetaInfo
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.springframework.web.client.RestTemplate

internal class DefaultReminderGroupTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
    }

    @TestFactory
    fun methodInvocations(): List<DynamicTest> = DynamicGroupTests.methodInvocations(testCases = testCases(), mockTemplate = mockTemplate)

    private fun testCases() = listOf(
            MetaInfo("reminders.delete", SuccessfulRemindersDeleteResponse.sample(), ErrorRemindersDeleteResponse.sample(), RemindersDeleteRequest.sample(), SpringRemindersDeleteMethod("", mockTemplate)),
            MetaInfo("reminders.list", SuccessfulRemindersListResponse.sample(), ErrorRemindersListResponse.sample(), Unit, SpringRemindersListMethod("", mockTemplate)),
            MetaInfo("reminders.info", SuccessfulRemindersInfoResponse.sample(), ErrorRemindersInfoResponse.sample(), Unit, SpringRemindersInfoMethod("", mockTemplate)),
            MetaInfo("reminders.complete", SuccessfulRemindersCompleteResponse.sample(), ErrorRemindersCompleteResponse.sample(), Unit, SpringRemindersCompleteMethod("", mockTemplate)),
            MetaInfo("reminders.add", SuccessfulRemindersAddResponse.sample(), ErrorRemindersAddResponse.sample(), Unit, SpringRemindersAddMethod("", mockTemplate))
    )
}
