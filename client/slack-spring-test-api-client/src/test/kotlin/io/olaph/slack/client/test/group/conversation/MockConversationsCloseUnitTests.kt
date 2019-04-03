package io.olaph.slack.client.test.group.conversation

import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.conversation.sample
import io.olaph.slack.dto.jackson.group.conversations.ConversationCloseRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationCloseResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationCloseResponse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("conversations.close Method")
class MockConversationsCloseUnitTests {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction : (SuccessfulConversationCloseResponse?) -> Any = mock {  }
        val failureFunction : (ErrorConversationCloseResponse?) -> Any = mock {  }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().close("")},
                successFunction, SuccessfulConversationCloseResponse.sample(),
                failureFunction, ErrorConversationCloseResponse.sample(),
                ConversationCloseRequest.sample())
    }
}
