package io.olaph.slack.client.test.group.conversations

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.conversations.ConversationMembersRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationMembersResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationMembersResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MockConversationsMembersUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulConversationMembersResponse?) -> Any = mock {}
        val failureFunction: (ErrorConversationMembersResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.conversation().members("") },
                successFunction, SuccessfulConversationMembersResponse.sample(),
                failureFunction, ErrorConversationMembersResponse.sample(),
                ConversationMembersRequest.sample())
    }
}
