package com.kreait.slack.api.test.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ChatGetPermalinkRequest
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatGetPermalinkResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatGetPermalinkResponse
import com.kreait.slack.api.contract.jackson.group.chat.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockChatGetPermalinkMethod")
class MockChatGetPermalinkUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulChatGetPermalinkResponse?) -> Any = mock {}
        val failureFunction: (ErrorChatGetPermalinkResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.chat().getPermalink("") },
                successFunction, SuccessfulChatGetPermalinkResponse.sample(),
                failureFunction, ErrorChatGetPermalinkResponse.sample(),
                ChatGetPermalinkRequest.sample())
    }
}
