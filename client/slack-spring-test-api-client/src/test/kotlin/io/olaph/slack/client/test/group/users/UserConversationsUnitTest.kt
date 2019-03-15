package io.olaph.slack.client.test.group.users

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.dto.jackson.group.users.ErrorUserConversationsResponse
import io.olaph.slack.dto.jackson.group.users.SlackUserConversationListRequest
import io.olaph.slack.dto.jackson.group.users.SuccessfulUserConversationsResponse
import io.olaph.slack.dto.jackson.group.users.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockUserConversationsMethod")
class UserConversationsUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulUserConversationsResponse?) -> Any = mock {}
        val failureFunction: (ErrorUserConversationsResponse?) -> Any = mock {}

        MockMethodTestHelper.verify(MockUserConversationsMethod(),
                successFunction, SuccessfulUserConversationsResponse.sample(),
                failureFunction, ErrorUserConversationsResponse.sample(),
                SlackUserConversationListRequest()
        )
    }


}
