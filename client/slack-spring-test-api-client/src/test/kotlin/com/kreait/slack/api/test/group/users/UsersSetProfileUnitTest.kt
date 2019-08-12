package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.test.MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersSetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersSetProfileResponse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("UserSetProfileMethod")
class UsersSetProfileUnitTest() {

    @DisplayName("Test Users.setProfile method")
    @Test
    fun userListTest() {
        val successFunction: (SuccessfulUsersSetProfileResponse?) -> Any = mock {}
        val failureFunction: (ErrorUsersSetProfileResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.users().setProfile("") },
                successFunction, SuccessfulUsersSetProfileResponse.sample(),
                failureFunction, ErrorUsersSetProfileResponse.sample(),
                Unit
        )
    }
}
