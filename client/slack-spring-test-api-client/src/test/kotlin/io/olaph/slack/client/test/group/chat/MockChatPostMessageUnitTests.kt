package io.olaph.slack.client.test.group.chat

import MockMethodTestHelper
import io.olaph.slack.dto.jackson.group.chat.ErrorPostEphemeralMessageResponse
import io.olaph.slack.dto.jackson.group.chat.SlackPostEphemeralMessageRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulPostEphemeralMessageResponse
import io.olaph.slack.dto.jackson.group.chat.sample
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.dto.jackson.group.chat.ErrorPostMessageResponse
import io.olaph.slack.dto.jackson.group.chat.SlackPostMessageRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulPostMessageResponse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.Instant


@DisplayName("MockChatPostMessageMethod")
class MockChatPostMessageUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulPostMessageResponse?) -> Any = mock {}
        val failureFunction: (ErrorPostMessageResponse?) -> Any = mock {}

        MockMethodTestHelper.verify(MockChatPostMessageMethod(),
                successFunction, SuccessfulPostMessageResponse(true, Instant.now().toString(),""),
                failureFunction, ErrorPostMessageResponse(false, ""),
                SlackPostMessageRequest.sample())
    }
}
