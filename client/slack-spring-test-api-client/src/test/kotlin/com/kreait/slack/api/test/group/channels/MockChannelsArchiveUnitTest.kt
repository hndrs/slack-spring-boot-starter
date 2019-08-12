package com.kreait.slack.api.test.group.channels

import com.kreait.slack.api.test.MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelArchiveResponse
import com.kreait.slack.api.contract.jackson.group.channels.SlackChannelsArchiveRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelArchiveResponse
import com.kreait.slack.api.contract.jackson.group.channels.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock ChannelsArchiveMethod")
class MockChannelsArchiveUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulChannelArchiveResponse?) -> Any = mock { }
        val failureFunction: (ErrorChannelArchiveResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.channel().archive("") },
                successFunction, SuccessfulChannelArchiveResponse.sample(),
                failureFunction, ErrorChannelArchiveResponse.sample(),
                SlackChannelsArchiveRequest.sample())
    }
}
