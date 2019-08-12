package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersSetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.SlackUsersSetPresenceRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersSetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@Suppress("UNCHECKED_CAST")
class UsersSetPresenceUnitTest {

    @DisplayName("Users setPresence Method")
    @Test
    fun testUsersSetPresence() {
        val successFunction: (SuccessfulUsersSetPresenceResponse?) -> Any = mock {  }
        val failureFunction: (ErrorUsersSetPresenceResponse?) -> Any = mock {  }
        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify({mockClient.users().setPresence("")},
                successFunction, SuccessfulUsersSetPresenceResponse.sample(),
                failureFunction, ErrorUsersSetPresenceResponse.sample(),
                SlackUsersSetPresenceRequest.sample())
    }
}
