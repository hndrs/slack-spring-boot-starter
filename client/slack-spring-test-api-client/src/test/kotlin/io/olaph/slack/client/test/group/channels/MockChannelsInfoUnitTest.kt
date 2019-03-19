package io.olaph.slack.client.test.group.channels

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.channels.ChannelInfoChannel
import io.olaph.slack.dto.jackson.group.channels.ErrorGetChannelInfoResponse
import io.olaph.slack.dto.jackson.group.channels.SlackChannelsInfoRequest
import io.olaph.slack.dto.jackson.group.channels.SuccessfulGetChannelInfoResponse
import io.olaph.slack.dto.jackson.group.channels.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("MockChannelsInfoMethod")
class MockChannelsInfoUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulGetChannelInfoResponse?) -> Any = mock { }
        val failureFunction: (ErrorGetChannelInfoResponse?) -> Any = mock { }

        MockMethodTestHelper.verify(MockSlackClient().channel().info(""),
                successFunction, SuccessfulGetChannelInfoResponse(true, ChannelInfoChannel()),
                failureFunction, ErrorGetChannelInfoResponse(false, ""),
                SlackChannelsInfoRequest.sample())
    }
}
