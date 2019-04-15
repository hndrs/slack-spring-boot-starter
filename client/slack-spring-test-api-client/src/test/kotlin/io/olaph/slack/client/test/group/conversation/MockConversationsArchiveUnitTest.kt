package io.olaph.slack.client.test.group.conversation

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.conversations.ConversationArchiveRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationArchiveResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationArchiveResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
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