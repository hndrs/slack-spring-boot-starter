package io.olaph.slack.client.test.group.conversation

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.conversations.ConversationsRenameRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationsRenameResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationsRenameResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MockConversationsRenameUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulConversationsRenameResponse?) -> Any = mock { }
        val failureFunciton: (ErrorConversationsRenameResponse?) -> Any = mock { }

        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().rename("") },
                successFunction, SuccessfulConversationsRenameResponse.sample(),
                failureFunciton, ErrorConversationsRenameResponse.sample(),
                ConversationsRenameRequest.sample())
    }
}