package io.olaph.slack.client.test.group.users

import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.users.ErrorUsersGetPresenceResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersGetPresenceResponse
import io.olaph.slack.dto.jackson.group.users.UsersGetPresenceRequest
import io.olaph.slack.dto.jackson.group.users.sample
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