package io.olaph.slack.client.test.group.conversation

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.conversations.ConversationsInviteRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationInviteResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationInviteResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("conversations.invite Method")
class MockConversationsInviteMethodUnitTests {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulConversationInviteResponse?) -> Any = mock { }
        val failureFunction: (ErrorConversationInviteResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().invite("") },
                successFunction, SuccessfulConversationInviteResponse.sample(),
                failureFunction, ErrorConversationInviteResponse.sample(),
                ConversationsInviteRequest.sample())
    }
}
