package io.olaph.slack.client.test.group.conversation

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.conversations.ConversationsHistoryRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationHistoryResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationHistoryResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MockConversationsHistoryUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulConversationHistoryResponse?) -> Any = mock { }
        val failureFunciton: (ErrorConversationHistoryResponse?) -> Any = mock { }


        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().history("") },
                successFunction, SuccessfulConversationHistoryResponse.sample(),
                failureFunciton, ErrorConversationHistoryResponse.sample(),
                ConversationsHistoryRequest.sample())
    }
}