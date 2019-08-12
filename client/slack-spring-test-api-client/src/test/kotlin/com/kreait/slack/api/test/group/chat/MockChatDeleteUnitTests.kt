package com.kreait.slack.api.test.group.chat

import com.kreait.slack.api.test.MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatDeleteResponse
import com.kreait.slack.api.contract.jackson.group.chat.SlackChatDeleteRequest
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatDeleteResponse
import com.kreait.slack.api.contract.jackson.group.chat.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockChatDeleteMethod")
class MockChatDeleteUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulChatDeleteResponse?) -> Any = mock {}
        val failureFunction: (ErrorChatDeleteResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.chat().delete("") },
                successFunction, SuccessfulChatDeleteResponse.sample(),
                failureFunction, ErrorChatDeleteResponse.sample(),
                SlackChatDeleteRequest.sample())
    }
}
