package io.olaph.slack.client.test.group.channels

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.channels.ErrorChannelArchiveResponse
import io.olaph.slack.dto.jackson.group.channels.SlackChannelsArchiveRequest
import io.olaph.slack.dto.jackson.group.channels.SuccessfulChannelArchiveResponse
import io.olaph.slack.dto.jackson.group.channels.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock ChannelsArchiveMethod")
class MockChannelsArchiveUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulChannelArchiveResponse?) -> Any = mock { }
        val failureFunction: (ErrorChannelArchiveResponse?) -> Any = mock { }

        MockMethodTestHelper.verify(MockSlackClient().channel().archive(""),
                successFunction, SuccessfulChannelArchiveResponse.sample(),
                failureFunction, ErrorChannelArchiveResponse.sample(),
                SlackChannelsArchiveRequest.sample())
    }
}
