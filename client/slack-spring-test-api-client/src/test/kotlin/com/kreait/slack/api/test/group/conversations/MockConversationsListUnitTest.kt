package com.kreait.slack.api.test.group.conversations

import com.kreait.slack.api.test.MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsListRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationListResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationListResponse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MockConversationsListUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulConversationListResponse?) -> Any = mock {}
        val failureFunction: (ErrorConversationListResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().list("") },
                successFunction, SuccessfulConversationListResponse.sample(),
                failureFunction, ErrorConversationListResponse.sample(),
                ConversationsListRequest.sample())
    }
}
