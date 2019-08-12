package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorGetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulGetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.GetPresenceRequest
import com.kreait.slack.api.contract.jackson.group.users.sample
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

        val successFunction: (SuccessfulGetPresenceResponse?) -> Any = mock {  }
        val failureFunction: (ErrorGetPresenceResponse?) -> Any = mock {  }

        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify( { mockClient.users().getPresence("") },
                successFunction, SuccessfulGetPresenceResponse.sample(),
                failureFunction, ErrorGetPresenceResponse.sample(),
                GetPresenceRequest.sample())
    }
}
