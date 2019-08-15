package com.kreait.slack.api.test.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelRepliesRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelRepliesResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelRepliesResponse
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
        val successFunction: (SuccessfulChannelRepliesResponse?) -> Any = mock { }
        val failureFunction: (ErrorChannelRepliesResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.channel().replies("") },
                successFunction, SuccessfulChannelRepliesResponse.sample(),
                failureFunction, ErrorChannelRepliesResponse.sample(),
                ChannelRepliesRequest.sample())
    }
}
