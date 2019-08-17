package com.kreait.slack.api.test.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsHistoryRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsHistoryResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsHistoryResponse
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
        val successFunction: (SuccessfulChannelsHistoryResponse?) -> Any = mock { }
        val failureFunction: (ErrorChannelsHistoryResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.channel().history("") },
                successFunction, SuccessfulChannelsHistoryResponse.sample(),
                failureFunction, ErrorChannelsHistoryResponse.sample(),
                ChannelsHistoryRequest.sample())
    }
}
