package io.olaph.slack.client.test.group.conversation

import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.conversations.ConversationsSetPurposeRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationSetPurposeResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationSetPurposeResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MockConversationsSetPurposeUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulConversationSetPurposeResponse?) -> Any = mock{ }
        val failureFunciton: (ErrorConversationSetPurposeResponse?) -> Any = mock { }

        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().setPurpose("") },
                successFunction, SuccessfulConversationSetPurposeResponse.sample(),
                failureFunciton, ErrorConversationSetPurposeResponse.sample(),
                ConversationsSetPurposeRequest.sample())
    }
}