package io.olaph.slack.client.test.group.users

import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.users.ErrorUsersSetPresenceResponse
import io.olaph.slack.dto.jackson.group.users.SlackUsersSetPresenceRequest
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersSetPresenceResponse
import io.olaph.slack.dto.jackson.group.users.sample
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
                SlackUsersSetPresenceRequest(""))
    }

}