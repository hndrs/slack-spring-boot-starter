package io.olaph.slack.client.test.group.conversation

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.conversations.ConversationsSetTopicRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationSetTopicResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationSetTopicResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MockConversationsSetTopicUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulConversationSetTopicResponse?) -> Any = mock { }
        val failureFunction: (ErrorConversationSetTopicResponse?) -> Any = mock { }

        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().setTopic("") },
                successFunction, SuccessfulConversationSetTopicResponse.sample(),
                failureFunction, ErrorConversationSetTopicResponse.sample(),
                ConversationsSetTopicRequest.sample())
    }
}
