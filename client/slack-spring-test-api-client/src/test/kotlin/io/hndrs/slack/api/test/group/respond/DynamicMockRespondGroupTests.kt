package io.hndrs.slack.api.test.group.respond

import io.hndrs.slack.api.contract.jackson.group.respond.RespondMessageRequest
import io.hndrs.slack.api.contract.jackson.group.respond.sample
import io.hndrs.slack.api.test.DynamicMockGroupTests
import io.hndrs.slack.api.test.MockMetaInfo
import io.hndrs.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class DynamicMockRespondGroupTests {

    @TestFactory
    fun methodInvocations(): List<DynamicTest> = DynamicMockGroupTests.methodInvocations(testCases = testCases())

    private val client = MockSlackClient()

    private fun testCases() = listOf(
            MockMetaInfo({ client.respond().message("") }, mock { }, Unit, mock { }, Unit, RespondMessageRequest.sample())
    )
}
