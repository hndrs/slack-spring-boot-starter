package io.olaph.slack.client.test.group.conversations

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.conversations.ConversationsListRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationListResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationListResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MockConversationsListUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulConversationListResponse?) -> Any = mock {}
        val failureFunction: (ErrorConversationListResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().list("") },
                successFunction, SuccessfulConversationListResponse.sample(),
                failureFunction, ErrorConversationListResponse.sample(),
                ConversationsListRequest.sample())
    }
}
