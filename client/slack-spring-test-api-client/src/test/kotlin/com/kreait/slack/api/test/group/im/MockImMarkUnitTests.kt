package com.kreait.slack.api.test.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImMarkResponse
import com.kreait.slack.api.contract.jackson.group.im.ImMarkRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImMarkResponse
import com.kreait.slack.api.contract.jackson.group.im.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
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
                ImMarkRequest.sample()
        )
    }
}
