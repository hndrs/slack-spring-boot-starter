package com.kreait.slack.api.test.group.im

import com.kreait.slack.api.test.MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.api.contract.jackson.group.im.ErrorImListResponse
import com.kreait.slack.api.contract.jackson.group.im.SlackImListRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImListResponse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockImListMethod")
class MockImListUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulImListResponse?) -> Any = mock {}
        val failureFunction: (ErrorImListResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.im().list("") },
                successFunction, SuccessfulImListResponse.sample(),
                failureFunction, ErrorImListResponse.sample(),
                SlackImListRequest.sample()
        )
    }
}
