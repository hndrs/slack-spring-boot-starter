package com.kreait.slack.api.test.group.conversation

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsInfoRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationsInfoResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationsInfoResponse
import com.kreait.slack.api.contract.jackson.group.conversations.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("conversations.info Method")
class MockConversationsInfoUnitTests {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulConversationsInfoResponse?) -> Any = mock { }
        val failureFunction: (ErrorConversationsInfoResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().info("") },
                successFunction, SuccessfulConversationsInfoResponse.sample(),
                failureFunction, ErrorConversationsInfoResponse.sample(),
                ConversationsInfoRequest.sample())
    }
}
