package com.kreait.slack.api.test.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImOpenResponse
import com.kreait.slack.api.contract.jackson.group.im.SlackImOpenRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImOpenResponse
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockImOpenMethod")
class MockImOpenUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulImOpenResponse?) -> Any = mock {}
        val failureFunction: (ErrorImOpenResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.im().open("") },
                successFunction, SuccessfulImOpenResponse.sample(),
                failureFunction, ErrorImOpenResponse.sample(),
                SlackImOpenRequest.sample()
        )
    }
}
