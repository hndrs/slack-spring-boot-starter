package com.kreait.slack.api.test.group.conversations

import com.kreait.slack.api.test.MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsRepliesRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationRepliesResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationRepliesResponse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MockConversationsRepliesUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulConversationRepliesResponse?) -> Any = mock {}
        val failureFunction: (ErrorConversationRepliesResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().replies("") },
                successFunction, SuccessfulConversationRepliesResponse.sample(),
                failureFunction, ErrorConversationRepliesResponse.sample(),
                ConversationsRepliesRequest.sample())
    }
}
