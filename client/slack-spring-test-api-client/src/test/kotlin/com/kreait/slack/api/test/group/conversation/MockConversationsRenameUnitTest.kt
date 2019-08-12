package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsRenameRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationsRenameResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationsRenameResponse
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MockConversationsRenameUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulConversationsRenameResponse?) -> Any = mock { }
        val failureFunciton: (ErrorConversationsRenameResponse?) -> Any = mock { }

        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().rename("") },
                successFunction, SuccessfulConversationsRenameResponse.sample(),
                failureFunciton, ErrorConversationsRenameResponse.sample(),
                ConversationsRenameRequest.sample())
    }
}
