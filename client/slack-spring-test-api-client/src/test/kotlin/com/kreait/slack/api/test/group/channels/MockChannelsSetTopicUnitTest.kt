package com.kreait.slack.api.test.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsSetTopicRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.channels.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock ChannelsSetTopicMethod")
class MockChannelsSetTopicUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulChannelsSetTopicResponse?) -> Any = mock { }
        val failureFunction: (ErrorChannelsSetTopicResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.channel().setTopic("") },
                successFunction, SuccessfulChannelsSetTopicResponse.sample(),
                failureFunction, ErrorChannelsSetTopicResponse.sample(),
                ChannelsSetTopicRequest.sample())
    }
}
