package io.olaph.slack.client.test.group.im

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.im.ErrorImRepliesResponse
import io.olaph.slack.dto.jackson.group.im.SlackImRepliesRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImRepliesResponse
import io.olaph.slack.dto.jackson.group.im.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockChatPostEphemeralMethod")
class MockImRepliesUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulImRepliesResponse?) -> Any = mock {}
        val failureFunction: (ErrorImRepliesResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.im().replies("") },
                successFunction, SuccessfulImRepliesResponse.sample(),
                failureFunction, ErrorImRepliesResponse.sample(),
                SlackImRepliesRequest.sample()
        )
    }


}
