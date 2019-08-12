package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.test.MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationArchiveRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationArchiveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationArchiveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("conversations.archive Method")
class MockConversationsArchiveUnitTests {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulConversationArchiveResponse?) -> Any = mock { }
        val failureFunction: (ErrorConversationArchiveResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().archive("") },
                successFunction, SuccessfulConversationArchiveResponse.sample(),
                failureFunction, ErrorConversationArchiveResponse.sample(),
                ConversationArchiveRequest.sample())
    }
}
