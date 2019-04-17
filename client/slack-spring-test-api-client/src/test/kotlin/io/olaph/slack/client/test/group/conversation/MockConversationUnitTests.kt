package io.olaph.slack.client.test.group.conversation

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.conversations.sample
import io.olaph.slack.dto.jackson.group.conversations.ConversationCreateRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationCreateResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationCreateResponse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockChatConversationCreateMethod")
class MockConversationUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulConversationCreateResponse?) -> Any = mock {}
        val failureFunction: (ErrorConversationCreateResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().create("") },
                successFunction, SuccessfulConversationCreateResponse.sample(),
                failureFunction, ErrorConversationCreateResponse.sample(),
                ConversationCreateRequest.sample())
    }
}
