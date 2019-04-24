package io.olaph.slack.client.test.group.conversation


import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.conversations.ConversationJoinRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationJoinResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationJoinResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("conversations.join Method")
class MockConversationsJoinUnitTest {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction : (SuccessfulConversationJoinResponse?) -> Any = mock {  }
        val failureFunciton : (ErrorConversationJoinResponse?) -> Any = mock {  }
        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify( {mockClient.conversation().join("")},
                successFunction, SuccessfulConversationJoinResponse.sample(),
                failureFunciton, ErrorConversationJoinResponse.sample(),
                ConversationJoinRequest.sample())
    }
}