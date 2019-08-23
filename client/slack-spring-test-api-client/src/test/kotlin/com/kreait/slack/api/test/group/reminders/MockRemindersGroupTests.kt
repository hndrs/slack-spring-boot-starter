package com.kreait.slack.api.test.group.reminders

import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersAddResponse
import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersCompleteResponse
import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersDeleteResponse
import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersInfoResponse
import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersListResponse
import com.kreait.slack.api.contract.jackson.group.reminders.RemindersAddRequest
import com.kreait.slack.api.contract.jackson.group.reminders.RemindersCompleteRequest
import com.kreait.slack.api.contract.jackson.group.reminders.RemindersDeleteRequest
import com.kreait.slack.api.contract.jackson.group.reminders.RemindersInfoRequest
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersAddResponse
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersCompleteResponse
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersDeleteResponse
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersInfoResponse
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersListResponse
import com.kreait.slack.api.contract.jackson.group.reminders.sample
import com.kreait.slack.api.test.DynamicMockGroupTests
import com.kreait.slack.api.test.MockMetaInfo
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class MockRemindersGroupTests {

    @TestFactory
    fun methodInvocations(): List<DynamicTest> = DynamicMockGroupTests.methodInvocations(testCases = testCases())

    private fun testCases() = listOf(
            MockMetaInfo(MockRemindersDeleteMethod(), mock { }, SuccessfulRemindersDeleteResponse.sample(), mock { }, ErrorRemindersDeleteResponse.sample(), RemindersDeleteRequest.sample()),
            MockMetaInfo(MockRemindersListMethod(), mock {}, SuccessfulRemindersListResponse.sample(), mock {}, ErrorRemindersListResponse.sample(), Unit),
            MockMetaInfo(MockRemindersInfoMethod(), mock {}, SuccessfulRemindersInfoResponse.sample(), mock {}, ErrorRemindersInfoResponse.sample(), RemindersInfoRequest.sample()),
            MockMetaInfo(MockRemindersCompleteMethod(), mock {}, SuccessfulRemindersCompleteResponse.sample(), mock {}, ErrorRemindersCompleteResponse.sample(), RemindersCompleteRequest.sample()),
            MockMetaInfo(MockRemindersDeleteMethod(), mock { }, SuccessfulRemindersDeleteResponse.sample(), mock { }, ErrorRemindersDeleteResponse.sample(), RemindersDeleteRequest.sample()),
            MockMetaInfo(MockRemindersAddMethod(), mock { }, SuccessfulRemindersAddResponse.sample(), mock { }, ErrorRemindersAddResponse.sample(), RemindersAddRequest.sample())
    )
}
