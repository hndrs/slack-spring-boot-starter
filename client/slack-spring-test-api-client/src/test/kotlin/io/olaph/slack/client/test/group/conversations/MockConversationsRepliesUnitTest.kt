package io.olaph.slack.client.test.group.conversations

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.conversations.ConversationsRepliesRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationRepliesResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationRepliesResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MockConversationsRepliesUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulConversationRepliesResponse?) -> Any = mock {}
        val failureFunction: (ErrorConversationRepliesResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().replies("") },
                successFunction, SuccessfulConversationRepliesResponse.sample(),
                failureFunction, ErrorConversationRepliesResponse.sample(),
                ConversationsRepliesRequest.sample())
    }
}
