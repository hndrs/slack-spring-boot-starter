package com.kreait.slack.api.test.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelSetPurposeRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.channels.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock ChannelsSetPurposeMethod")
class MockChannelsSetPurposeUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulChannelSetPurposeResponse?) -> Any = mock { }
        val failureFunction: (ErrorChannelSetPurposeResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.channel().setPurpose("") },
                successFunction, SuccessfulChannelSetPurposeResponse.sample(),
                failureFunction, ErrorChannelSetPurposeResponse.sample(),
                ChannelSetPurposeRequest.sample())
    }
}
