package com.kreait.slack.api.test.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ChatMeMessageRequest
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatMeMessageResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatMeMessageResponse
import com.kreait.slack.api.contract.jackson.group.chat.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockChatMeMessageMethod")
class MockChatMessageUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulChatMeMessageResponse?) -> Any = mock {}
        val failureFunction: (ErrorChatMeMessageResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.chat().meMessage("") },
                successFunction, SuccessfulChatMeMessageResponse.sample(),
                failureFunction, ErrorChatMeMessageResponse.sample(),
                ChatMeMessageRequest.sample())
    }
}
