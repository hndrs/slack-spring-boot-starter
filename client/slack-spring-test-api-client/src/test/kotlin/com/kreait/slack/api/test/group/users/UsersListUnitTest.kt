package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.test.MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.api.contract.jackson.group.users.ErrorUserListResponse
import com.kreait.slack.api.contract.jackson.group.users.SlackUserListRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUserListResponse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("UsersList Method")
class UsersListUnitTest {

    @DisplayName("Test Users.list method")
    @Test
    fun testUsersList() {
        val successFunction: (SuccessfulUserListResponse?) -> Any = mock {}
        val failureFunction: (ErrorUserListResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.users().list("") },
                successFunction, SuccessfulUserListResponse.sample(),
                failureFunction, ErrorUserListResponse.sample(),
                SlackUserListRequest()
        )
    }
}
