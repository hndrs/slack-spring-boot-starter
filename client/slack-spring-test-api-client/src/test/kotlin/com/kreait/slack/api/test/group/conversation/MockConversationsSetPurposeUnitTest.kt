package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsSetPurposeRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.conversations.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MockConversationsSetPurposeUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulConversationSetPurposeResponse?) -> Any = mock{ }
        val failureFunciton: (ErrorConversationSetPurposeResponse?) -> Any = mock { }

        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().setPurpose("") },
                successFunction, SuccessfulConversationSetPurposeResponse.sample(),
                failureFunciton, ErrorConversationSetPurposeResponse.sample(),
                ConversationsSetPurposeRequest.sample())
    }
}
