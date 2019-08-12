package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUserConversationsResponse
import com.kreait.slack.api.contract.jackson.group.users.SlackUserConversationListRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUserConversationsResponse
import com.kreait.slack.api.contract.jackson.group.users.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockUserConversationsMethod")
class UserConversationsUnitTest {

    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulUserConversationsResponse?) -> Any = mock {}
        val failureFunction: (ErrorUserConversationsResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.users().conversations("") },
                successFunction, SuccessfulUserConversationsResponse.sample(),
                failureFunction, ErrorUserConversationsResponse.sample(),
                SlackUserConversationListRequest()
        )
    }


}
