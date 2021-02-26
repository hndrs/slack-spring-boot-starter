package io.hndrs.slack.api.test.group.reminders

import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersAddResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersCompleteResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersDeleteResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersInfoResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.ErrorRemindersListResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.RemindersAddRequest
import io.hndrs.slack.api.contract.jackson.group.reminders.RemindersCompleteRequest
import io.hndrs.slack.api.contract.jackson.group.reminders.RemindersDeleteRequest
import io.hndrs.slack.api.contract.jackson.group.reminders.RemindersInfoRequest
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersAddResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersCompleteResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersDeleteResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersInfoResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.SuccessfulRemindersListResponse
import io.hndrs.slack.api.contract.jackson.group.reminders.sample
import io.hndrs.slack.api.test.DynamicMockGroupTests
import io.hndrs.slack.api.test.MockMetaInfo
import io.hndrs.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class MockRemindersGroupTests {

    @TestFactory
    fun methodInvocations(): List<DynamicTest> = DynamicMockGroupTests.methodInvocations(testCases = testCases())

    private val client = MockSlackClient()

    private fun testCases() = listOf(
            MockMetaInfo({ client.reminders().delete("") }, mock { }, SuccessfulRemindersDeleteResponse.sample(), mock { }, ErrorRemindersDeleteResponse.sample(), RemindersDeleteRequest.sample()),
            MockMetaInfo({ client.reminders().list("") }, mock {}, SuccessfulRemindersListResponse.sample(), mock {}, ErrorRemindersListResponse.sample(), Unit),
            MockMetaInfo({ client.reminders().info("") }, mock {}, SuccessfulRemindersInfoResponse.sample(), mock {}, ErrorRemindersInfoResponse.sample(), RemindersInfoRequest.sample()),
            MockMetaInfo({ client.reminders().complete("") }, mock {}, SuccessfulRemindersCompleteResponse.sample(), mock {}, ErrorRemindersCompleteResponse.sample(), RemindersCompleteRequest.sample()),
            MockMetaInfo({ client.reminders().delete("") }, mock { }, SuccessfulRemindersDeleteResponse.sample(), mock { }, ErrorRemindersDeleteResponse.sample(), RemindersDeleteRequest.sample()),
            MockMetaInfo({ client.reminders().add("") }, mock { }, SuccessfulRemindersAddResponse.sample(), mock { }, ErrorRemindersAddResponse.sample(), RemindersAddRequest.sample())
    )
}
