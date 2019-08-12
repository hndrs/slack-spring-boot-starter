package com.kreait.slack.api.test.group.respond

import com.kreait.slack.api.contract.jackson.group.respond.SlackRespondMessageRequest
import com.kreait.slack.api.contract.jackson.group.respond.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockRespondMessageMethod")
class MockChatRespondEphemeralUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (Unit?) -> Any = mock {}
        val failureFunction: (Unit?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.respond().message("") },
                successFunction, Unit,
                failureFunction, Unit,
                SlackRespondMessageRequest.sample()
        )
    }


}
