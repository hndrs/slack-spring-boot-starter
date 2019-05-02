package io.olaph.slack.client.test.group.users

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.users.ErrorUsersIdentityResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersIdentityResponse
import io.olaph.slack.dto.jackson.group.users.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("UserIdentityMethod")
class UsersIdentityUnitTest() {

    @DisplayName("Test Users.Identity method")
    @Test
    fun userListTest() {
        val successFunction: (SuccessfulUsersIdentityResponse?) -> Any = mock {}
        val failureFunction: (ErrorUsersIdentityResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.users().identity("") },
                successFunction, SuccessfulUsersIdentityResponse.sample(),
                failureFunction, ErrorUsersIdentityResponse.sample(),
                Unit
        )
    }
}
