package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsLeaveRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationLeaveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationLeaveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("conversations.leave Method")
class MockConversationsLeaveUnitTests {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulConversationLeaveResponse?) -> Any = mock { }
        val failureFunction: (ErrorConversationLeaveResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().leave("") },
                successFunction, SuccessfulConversationLeaveResponse.sample(),
                failureFunction, ErrorConversationLeaveResponse.sample(),
                ConversationsLeaveRequest.sample())
    }
}
