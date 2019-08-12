package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.test.MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationCreateRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationCreateResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationCreateResponse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockChatConversationCreateMethod")
class MockConversationUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulConversationCreateResponse?) -> Any = mock {}
        val failureFunction: (ErrorConversationCreateResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().create("") },
                successFunction, SuccessfulConversationCreateResponse.sample(),
                failureFunction, ErrorConversationCreateResponse.sample(),
                ConversationCreateRequest.sample())
    }
}
