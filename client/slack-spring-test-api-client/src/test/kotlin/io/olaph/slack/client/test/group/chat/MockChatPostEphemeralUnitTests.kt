package io.olaph.slack.client.test.group.chat

import MockMethodTestHelper
import io.olaph.slack.dto.jackson.group.chat.ErrorPostEphemeralMessageResponse
import io.olaph.slack.dto.jackson.group.chat.SlackPostEphemeralMessageRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulPostEphemeralMessageResponse
import io.olaph.slack.dto.jackson.group.chat.sample
import com.nhaarman.mockitokotlin2.mock
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

        MockMethodTestHelper.verify(MockChatPostEphemeralMethod(),
                successFunction, SuccessfulPostEphemeralMessageResponse(true, Instant.now().toString()),
                failureFunction, ErrorPostEphemeralMessageResponse(false, ""),
                SlackPostEphemeralMessageRequest.sample()
        )
    }


}
