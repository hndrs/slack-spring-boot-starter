package io.olaph.slack.client.test.group.channels

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.channels.ErrorChannelInviteResponse
import io.olaph.slack.dto.jackson.group.channels.SlackChannelInviteRequest
import io.olaph.slack.dto.jackson.group.channels.SuccessfulChannelInviteResponse
import io.olaph.slack.dto.jackson.group.channels.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock ChannelsInviteMethod")
class MockChannelsInviteUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulChannelInviteResponse?) -> Any = mock { }
        val failureFunction: (ErrorChannelInviteResponse?) -> Any = mock { }

        MockMethodTestHelper.verify(MockSlackClient().channel().invite(""),
                successFunction, SuccessfulChannelInviteResponse.sample(),
                failureFunction, ErrorChannelInviteResponse.sample(),
                SlackChannelInviteRequest.sample())
    }
}
