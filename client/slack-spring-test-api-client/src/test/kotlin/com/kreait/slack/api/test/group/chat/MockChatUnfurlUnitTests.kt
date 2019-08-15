package com.kreait.slack.api.test.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ChatUnfurlRequest
import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatUnfurlResponse
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatUnfurlResponse
import com.kreait.slack.api.contract.jackson.group.chat.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockChatUnfurlMethod")
class MockChatUnfurlUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulChatUnfurlResponse?) -> Any = mock {}
        val failureFunction: (ErrorChatUnfurlResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.chat().unfurl("") },
                successFunction, SuccessfulChatUnfurlResponse.sample(),
                failureFunction, ErrorChatUnfurlResponse.sample(),
                ChatUnfurlRequest.sample())
    }
}
