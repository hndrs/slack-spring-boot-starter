package com.kreait.slack.api.test.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsLeaveRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsLeaveResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsLeaveResponse
import com.kreait.slack.api.contract.jackson.group.channels.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock ChannelsLeaveMethod")
class MockChannelLeaveUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulChannelsLeaveResponse?) -> Any = mock { }
        val failureFunction: (ErrorChannelsLeaveResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.channel().leave("") },
                successFunction, SuccessfulChannelsLeaveResponse.sample(),
                failureFunction, ErrorChannelsLeaveResponse.sample(),
                ChannelsLeaveRequest.sample())
    }
}
