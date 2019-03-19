package io.olaph.slack.client.test.group.chat

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.dto.jackson.group.chat.ErrorPostMessageResponse
import io.olaph.slack.dto.jackson.group.chat.SlackPostMessageRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulPostMessageResponse
import io.olaph.slack.dto.jackson.group.chat.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockChatPostMessageMethod")
class MockChatPostMessageUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulPostMessageResponse?) -> Any = mock {}
        val failureFunction: (ErrorPostMessageResponse?) -> Any = mock {}

        MockMethodTestHelper.verify(MockChatPostMessageMethod(),
                successFunction, SuccessfulPostMessageResponse.sample(),
                failureFunction, ErrorPostMessageResponse.sample(),
                SlackPostMessageRequest.sample())
    }
}
