package io.hndrs.slack.api.test.group.dialog

import io.hndrs.slack.api.contract.jackson.group.dialog.ErrorOpenDialogResponse
import io.hndrs.slack.api.contract.jackson.group.dialog.OpenDialogRequest
import io.hndrs.slack.api.contract.jackson.group.dialog.SuccessfulOpenDialogResponse
import io.hndrs.slack.api.contract.jackson.group.dialog.sample
import io.hndrs.slack.api.test.DynamicMockGroupTests
import io.hndrs.slack.api.test.MockMetaInfo
import io.hndrs.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class DynamicDialogTests {


    @TestFactory
    fun methodInvocations(): List<DynamicTest> = DynamicMockGroupTests.methodInvocations(testCases = testCases())

    private val client = MockSlackClient()

    private fun testCases() = listOf(
            MockMetaInfo({ client.dialog().open("") }, mock { }, SuccessfulOpenDialogResponse.sample(), mock { }, ErrorOpenDialogResponse.sample(), OpenDialogRequest.sample())
    )
}
