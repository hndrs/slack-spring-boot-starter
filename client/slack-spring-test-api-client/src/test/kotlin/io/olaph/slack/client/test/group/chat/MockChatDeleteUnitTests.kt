package io.olaph.slack.client.test.group.chat

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.dto.jackson.group.chat.ErrorChatDeleteResponse
import io.olaph.slack.dto.jackson.group.chat.SlackChatDeleteRequest
import io.olaph.slack.dto.jackson.group.chat.SuccessfulChatDeleteResponse
import io.olaph.slack.dto.jackson.group.chat.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockChatDeleteMethod")
class MockChatDeleteUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulChatDeleteResponse?) -> Any = mock {}
        val failureFunction: (ErrorChatDeleteResponse?) -> Any = mock {}

        MockMethodTestHelper.verify(MockChatDeleteMethod(),
                successFunction, SuccessfulChatDeleteResponse.sample(),
                failureFunction, ErrorChatDeleteResponse.sample(),
                SlackChatDeleteRequest.sample())
    }
}
