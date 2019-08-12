package com.kreait.slack.api.test.group.conversations

import com.kreait.slack.api.test.MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationUnarchiveRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationUnarchiveResponse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MockConversationsUnarchiveUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulConversationUnarchiveResponse?) -> Any = mock {}
        val failureFunction: (ErrorConversationUnarchiveResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().unarchive("") },
                successFunction, SuccessfulConversationUnarchiveResponse.sample(),
                failureFunction, ErrorConversationUnarchiveResponse.sample(),
                ConversationUnarchiveRequest.sample())
    }
}
