package com.kreait.slack.api.test.group.im

import com.kreait.slack.api.test.MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.api.contract.jackson.group.im.ErrorImMarkResponse
import com.kreait.slack.api.contract.jackson.group.im.SlackImMarkRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImMarkResponse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockImOpenMethod")
class MockImMarkUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulImMarkResponse?) -> Any = mock {}
        val failureFunction: (ErrorImMarkResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.im().mark("") },
                successFunction, SuccessfulImMarkResponse.sample(),
                failureFunction, ErrorImMarkResponse.sample(),
                SlackImMarkRequest.sample()
        )
    }
}
