package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.test.MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsInviteRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationInviteResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationInviteResponse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("conversations.invite Method")
class MockConversationsInviteMethodUnitTests {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulConversationInviteResponse?) -> Any = mock { }
        val failureFunction: (ErrorConversationInviteResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().invite("") },
                successFunction, SuccessfulConversationInviteResponse.sample(),
                failureFunction, ErrorConversationInviteResponse.sample(),
                ConversationsInviteRequest.sample())
    }
}
