package io.olaph.slack.client.test.group.conversation

import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.conversations.ConversationsOpenRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationOpenResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationOpenResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("conversations.open Method")
class MockConversationsOpenUnitTest {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction : (SuccessfulConversationOpenResponse?) -> Any = mock {  }
        val failureFunction : (ErrorConversationOpenResponse?) -> Any = mock {  }

        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify({mockClient.conversation().open("")},
                successFunction, SuccessfulConversationOpenResponse.sample(),
                failureFunction, ErrorConversationOpenResponse.sample(),
                ConversationsOpenRequest.sample())
    }
}