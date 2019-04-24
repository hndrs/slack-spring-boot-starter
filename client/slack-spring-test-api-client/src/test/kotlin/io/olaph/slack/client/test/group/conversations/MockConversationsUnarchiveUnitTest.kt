package io.olaph.slack.client.test.group.conversations

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.conversations.ConversationUnarchiveRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationUnarchiveResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationUnarchiveResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MockConversationsUnarchiveUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulConversationUnarchiveResponse?) -> Any = mock {}
        val failureFunction: (ErrorConversationUnarchiveResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().unarchive("") },
                successFunction, SuccessfulConversationUnarchiveResponse.sample(),
                failureFunction, ErrorConversationUnarchiveResponse.sample(),
                ConversationUnarchiveRequest.sample())
    }
}
