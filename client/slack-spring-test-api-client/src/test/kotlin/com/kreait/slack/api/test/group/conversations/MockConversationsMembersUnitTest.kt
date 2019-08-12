package com.kreait.slack.api.test.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationMembersRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationMembersResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationMembersResponse
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MockConversationsMembersUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulConversationMembersResponse?) -> Any = mock {}
        val failureFunction: (ErrorConversationMembersResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().members("") },
                successFunction, SuccessfulConversationMembersResponse.sample(),
                failureFunction, ErrorConversationMembersResponse.sample(),
                ConversationMembersRequest.sample())
    }
}
