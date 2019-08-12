package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorSetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.SetPresenceRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulSetPresenceResponse
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
        val successFunction: (SuccessfulSetPresenceResponse?) -> Any = mock {  }
        val failureFunction: (ErrorSetPresenceResponse?) -> Any = mock {  }
        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify({mockClient.users().setPresence("")},
                successFunction, SuccessfulSetPresenceResponse.sample(),
                failureFunction, ErrorSetPresenceResponse.sample(),
                SetPresenceRequest.sample())
    }
}
