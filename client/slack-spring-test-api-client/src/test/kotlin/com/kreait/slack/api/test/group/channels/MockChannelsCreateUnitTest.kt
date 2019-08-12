package com.kreait.slack.api.test.group.channels

import com.kreait.slack.api.test.MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelCreateResponse
import com.kreait.slack.api.contract.jackson.group.channels.SlackChannelCreateRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelCreateResponse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock ChannelsCreateMethod")
class MockChannelsCreateUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulChannelCreateResponse?) -> Any = mock { }
        val failureFunction: (ErrorChannelCreateResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.channel().create("") },
                successFunction, SuccessfulChannelCreateResponse.sample(),
                failureFunction, ErrorChannelCreateResponse.sample(),
                SlackChannelCreateRequest.sample())
    }
}
