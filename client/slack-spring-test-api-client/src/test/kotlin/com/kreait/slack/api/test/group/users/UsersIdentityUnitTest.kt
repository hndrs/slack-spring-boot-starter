package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersIdentityResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersIdentityResponse
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
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
