package com.kreait.slack.api.test.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelInfoResponse
import com.kreait.slack.api.contract.jackson.group.channels.ChannelsInfoRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelInfoResponse
import com.kreait.slack.api.contract.jackson.group.channels.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("MockChannelsInfoMethod")
class MockChannelsInfoUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulChannelInfoResponse?) -> Any = mock { }
        val failureFunction: (ErrorChannelInfoResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.channel().info("") },
                successFunction, SuccessfulChannelInfoResponse.sample(),
                failureFunction, ErrorChannelInfoResponse.sample(),
                ChannelsInfoRequest.sample())
    }
}
