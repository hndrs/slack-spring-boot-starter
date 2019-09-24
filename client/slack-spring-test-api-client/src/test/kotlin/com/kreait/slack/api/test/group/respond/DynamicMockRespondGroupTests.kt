package com.kreait.slack.api.test.group.respond

import com.kreait.slack.api.contract.jackson.group.respond.RespondMessageRequest
import com.kreait.slack.api.contract.jackson.group.respond.sample
import com.kreait.slack.api.test.DynamicMockGroupTests
import com.kreait.slack.api.test.MockMetaInfo
import com.kreait.slack.api.test.MockSlackClient
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
