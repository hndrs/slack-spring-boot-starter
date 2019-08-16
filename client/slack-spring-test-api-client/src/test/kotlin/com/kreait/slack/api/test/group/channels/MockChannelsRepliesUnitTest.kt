package com.kreait.slack.api.test.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsRepliesRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsRepliesResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsRepliesResponse
import com.kreait.slack.api.contract.jackson.group.channels.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock ChannelsRepliesMethod")
class MockChannelsRepliesUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulChannelsRepliesResponse?) -> Any = mock { }
        val failureFunction: (ErrorChannelsRepliesResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.channel().replies("") },
                successFunction, SuccessfulChannelsRepliesResponse.sample(),
                failureFunction, ErrorChannelsRepliesResponse.sample(),
                ChannelsRepliesRequest.sample())
    }
}
