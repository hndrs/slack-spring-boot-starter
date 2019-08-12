package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationCloseRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationCloseResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationCloseResponse
import com.kreait.slack.api.contract.jackson.group.conversations.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("conversations.close Method")
class MockConversationsCloseUnitTests {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction : (SuccessfulConversationCloseResponse?) -> Any = mock {  }
        val failureFunction : (ErrorConversationCloseResponse?) -> Any = mock {  }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().close("")},
                successFunction, SuccessfulConversationCloseResponse.sample(),
                failureFunction, ErrorConversationCloseResponse.sample(),
                ConversationCloseRequest.sample())
    }
}
