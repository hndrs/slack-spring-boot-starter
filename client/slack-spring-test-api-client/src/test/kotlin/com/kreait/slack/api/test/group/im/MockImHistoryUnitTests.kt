package com.kreait.slack.api.test.group.im

import com.kreait.slack.api.test.MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.api.contract.jackson.group.im.ErrorImHistoryResponse
import com.kreait.slack.api.contract.jackson.group.im.SlackImHistoryRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImHistoryResponse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock im.history Method Test")
class MockImHistoryUnitTests {

    @DisplayName("Mocking Successful")
    @Test
    fun imHistoryMockSuccess() {

        val successFunction: (SuccessfulImHistoryResponse?) -> Any = mock {  }
        val failureFunction: (ErrorImHistoryResponse?) -> Any = mock {  }

        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify( { mockClient.im().history("") },
                successFunction, SuccessfulImHistoryResponse.sample(),
                failureFunction, ErrorImHistoryResponse.sample(),
                SlackImHistoryRequest.sample()
        )
    }
}