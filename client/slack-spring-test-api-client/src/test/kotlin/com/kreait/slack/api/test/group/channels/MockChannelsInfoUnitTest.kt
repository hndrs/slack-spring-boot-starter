package com.kreait.slack.api.test.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ErrorGetChannelInfoResponse
import com.kreait.slack.api.contract.jackson.group.channels.SlackChannelsInfoRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulGetChannelInfoResponse
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
        val successFunction: (SuccessfulGetChannelInfoResponse?) -> Any = mock { }
        val failureFunction: (ErrorGetChannelInfoResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.channel().info("") },
                successFunction, SuccessfulGetChannelInfoResponse.sample(),
                failureFunction, ErrorGetChannelInfoResponse.sample(),
                SlackChannelsInfoRequest.sample())
    }
}
