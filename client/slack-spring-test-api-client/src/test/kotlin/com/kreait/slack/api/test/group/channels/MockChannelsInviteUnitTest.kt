package com.kreait.slack.api.test.group.channels

import com.kreait.slack.api.test.MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelInviteResponse
import com.kreait.slack.api.contract.jackson.group.channels.SlackChannelInviteRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelInviteResponse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock ChannelsInviteMethod")
class MockChannelsInviteUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulChannelInviteResponse?) -> Any = mock { }
        val failureFunction: (ErrorChannelInviteResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.channel().invite("") },
                successFunction, SuccessfulChannelInviteResponse.sample(),
                failureFunction, ErrorChannelInviteResponse.sample(),
                SlackChannelInviteRequest.sample())
    }
}
