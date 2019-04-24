package io.olaph.slack.client.test.group.conversation

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.conversations.ConversationsInfoRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationsInfoResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationsInfoResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("conversations.info Method")
class MockConversationsInfoUnitTests {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulConversationsInfoResponse?) -> Any = mock { }
        val failureFunction: (ErrorConversationsInfoResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().info("") },
                successFunction, SuccessfulConversationsInfoResponse.sample(),
                failureFunction, ErrorConversationsInfoResponse.sample(),
                ConversationsInfoRequest.sample())
    }
}
