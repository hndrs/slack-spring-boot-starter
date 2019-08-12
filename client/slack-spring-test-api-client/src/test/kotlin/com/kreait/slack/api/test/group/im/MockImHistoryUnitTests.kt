package com.kreait.slack.api.test.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImHistoryResponse
import com.kreait.slack.api.contract.jackson.group.im.ImHistoryRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImHistoryResponse
import com.kreait.slack.api.contract.jackson.group.im.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
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
                ImHistoryRequest.sample()
        )
    }
}
