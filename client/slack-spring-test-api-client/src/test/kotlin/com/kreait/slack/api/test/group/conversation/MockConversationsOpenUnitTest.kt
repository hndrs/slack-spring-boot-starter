package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.test.MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsOpenRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationOpenResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationOpenResponse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("conversations.open Method")
class MockConversationsOpenUnitTest {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction : (SuccessfulConversationOpenResponse?) -> Any = mock {  }
        val failureFunction : (ErrorConversationOpenResponse?) -> Any = mock {  }

        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify({mockClient.conversation().open("")},
                successFunction, SuccessfulConversationOpenResponse.sample(),
                failureFunction, ErrorConversationOpenResponse.sample(),
                ConversationsOpenRequest.sample())
    }
}
