package com.kreait.slack.api.test.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelMarkRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelMarkResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelMarkResponse
import com.kreait.slack.api.contract.jackson.group.channels.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock ChannelsMarkMethod")
class MockChannelsMarkUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulChannelMarkResponse?) -> Any = mock { }
        val failureFunction: (ErrorChannelMarkResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.channel().mark("") },
                successFunction, SuccessfulChannelMarkResponse.sample(),
                failureFunction, ErrorChannelMarkResponse.sample(),
                ChannelMarkRequest.sample())
    }
}
