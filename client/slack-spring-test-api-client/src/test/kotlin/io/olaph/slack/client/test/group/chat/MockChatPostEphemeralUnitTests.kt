package io.olaph.slack.client.test.group.chat

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.chat.ErrorPostEphemeralResponse
import io.olaph.slack.dto.jackson.group.chat.SlackPostEphemeralRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulPostEphemeralResponse
import io.olaph.slack.dto.jackson.group.chat.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockChatPostEphemeralMethod")
class MockChatPostEphemeralUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulPostEphemeralResponse?) -> Any = mock {}
        val failureFunction: (ErrorPostEphemeralResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.chat().postEphemeral("") },
                successFunction, SuccessfulPostEphemeralResponse.sample(),
                failureFunction, ErrorPostEphemeralResponse.sample(),
                SlackPostEphemeralRequest.sample()
        )
    }


}
