package io.olaph.slack.client.test.group.chat

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.chat.ErrorPostEphemeralMessageResponse
import io.olaph.slack.dto.jackson.group.chat.SlackPostEphemeralMessageRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulPostEphemeralMessageResponse
import io.olaph.slack.dto.jackson.group.chat.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.Instant


@DisplayName("MockChatPostEphemeralMethod")
class MockChatPostEphemeralUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulPostEphemeralMessageResponse?) -> Any = mock {}
        val failureFunction: (ErrorPostEphemeralMessageResponse?) -> Any = mock {}

        MockMethodTestHelper.verify(MockSlackClient().chat().postEphemeral(""),
                successFunction, SuccessfulPostEphemeralMessageResponse(true, Instant.now().toString()),
                failureFunction, ErrorPostEphemeralMessageResponse(false, ""),
                SlackPostEphemeralMessageRequest.sample()
        )
    }


}
