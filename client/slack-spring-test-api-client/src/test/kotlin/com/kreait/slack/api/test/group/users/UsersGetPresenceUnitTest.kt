package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersGetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersGetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.UsersGetPresenceRequest
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("users.getPresence Method")
class UsersGetPresenceUnitTest {

    @DisplayName("users.getPresence Test")
    @Test
    fun usersGetPresenceTest() {

        val successFunction: (SuccessfulUsersGetPresenceResponse?) -> Any = mock {  }
        val failureFunction: (ErrorUsersGetPresenceResponse?) -> Any = mock {  }

        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify( { mockClient.users().getPresence("") },
                successFunction, SuccessfulUsersGetPresenceResponse.sample(),
                failureFunction, ErrorUsersGetPresenceResponse.sample(),
                UsersGetPresenceRequest.sample())
    }
}
