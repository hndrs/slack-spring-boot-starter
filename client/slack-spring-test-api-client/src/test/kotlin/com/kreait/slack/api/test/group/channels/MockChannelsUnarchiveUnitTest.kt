package com.kreait.slack.api.test.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsUnarchiveRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.channels.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock ChannelsUnarchiveMethod")
class MockChannelsUnarchiveUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulChannelUnarchiveResponse?) -> Any = mock { }
        val failureFunction: (ErrorChannelUnarchiveResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.channel().unarchive("") },
                successFunction, SuccessfulChannelUnarchiveResponse.sample(),
                failureFunction, ErrorChannelUnarchiveResponse.sample(),
                ChannelsUnarchiveRequest.sample())
    }
}
