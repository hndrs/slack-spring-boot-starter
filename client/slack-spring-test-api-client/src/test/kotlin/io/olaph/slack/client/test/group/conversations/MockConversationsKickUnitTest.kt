package io.olaph.slack.client.test.group.conversations

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.conversations.ConversationsKickRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationKickResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationKickResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MockConversationsKickUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulConversationKickResponse?) -> Any = mock {}
        val failureFunction: (ErrorConversationKickResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().kick("") },
                successFunction, SuccessfulConversationKickResponse.sample(),
                failureFunction, ErrorConversationKickResponse.sample(),
                ConversationsKickRequest.sample())
    }
}