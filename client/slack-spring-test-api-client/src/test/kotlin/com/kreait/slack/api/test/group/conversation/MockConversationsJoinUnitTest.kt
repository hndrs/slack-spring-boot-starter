package com.kreait.slack.api.test.group.conversation


import com.kreait.slack.api.contract.jackson.group.conversations.ConversationJoinRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationJoinResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationJoinResponse
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("conversations.join Method")
class MockConversationsJoinUnitTest {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction : (SuccessfulConversationJoinResponse?) -> Any = mock {  }
        val failureFunciton : (ErrorConversationJoinResponse?) -> Any = mock {  }
        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify( {mockClient.conversation().join("")},
                successFunction, SuccessfulConversationJoinResponse.sample(),
                failureFunciton, ErrorConversationJoinResponse.sample(),
                ConversationJoinRequest.sample())
    }
}
