package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorListResponse
import com.kreait.slack.api.contract.jackson.group.users.ListRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulListResponse
import com.kreait.slack.api.contract.jackson.group.users.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("UsersList Method")
class UsersListUnitTest {

    @DisplayName("Test Users.list method")
    @Test
    fun testUsersList() {
        val successFunction: (SuccessfulListResponse?) -> Any = mock {}
        val failureFunction: (ErrorListResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.users().list("") },
                successFunction, SuccessfulListResponse.sample(),
                failureFunction, ErrorListResponse.sample(),
                ListRequest()
        )
    }
}
