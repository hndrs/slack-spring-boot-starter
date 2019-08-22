package com.kreait.slack.api.test.group.reminders

import com.kreait.slack.api.contract.jackson.group.reminders.ErrorRemindersDeleteResponse
import com.kreait.slack.api.contract.jackson.group.reminders.SuccessfulRemindersDeleteResponse
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
            MockMetaInfo(MockRemindersDeleteMethod(), mock { },
                    SuccessfulRemindersDeleteResponse.sample(),
                    mock { },
                    ErrorRemindersDeleteResponse.sample(),
                    Unit)
    )
}