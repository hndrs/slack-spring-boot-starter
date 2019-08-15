package com.kreait.slack.api.test.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsHistoryRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelHistoryResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelHistoryResponse
import com.kreait.slack.api.contract.jackson.group.channels.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("MockChannelsHistoryMethod")
class MockChannelHistoryUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulChannelHistoryResponse?) -> Any = mock { }
        val failureFunction: (ErrorChannelHistoryResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.channel().history("") },
                successFunction, SuccessfulChannelHistoryResponse.sample(),
                failureFunction, ErrorChannelHistoryResponse.sample(),
                ChannelsHistoryRequest.sample())
    }
}
