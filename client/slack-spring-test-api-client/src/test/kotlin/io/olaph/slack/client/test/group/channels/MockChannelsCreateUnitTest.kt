package io.olaph.slack.client.test.group.channels

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.channels.ErrorChannelCreateResponse
import io.olaph.slack.dto.jackson.group.channels.SlackChannelCreateRequest
import io.olaph.slack.dto.jackson.group.channels.SuccessfulChannelCreateResponse
import io.olaph.slack.dto.jackson.group.channels.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock ChannelsCreateMethod")
class MockChannelsCreateUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulChannelCreateResponse?) -> Any = mock { }
        val failureFunction: (ErrorChannelCreateResponse?) -> Any = mock { }

        MockMethodTestHelper.verify(MockSlackClient().channel().create(""),
                successFunction, SuccessfulChannelCreateResponse.sample(),
                failureFunction, ErrorChannelCreateResponse.sample(),
                SlackChannelCreateRequest.sample())
    }
}
