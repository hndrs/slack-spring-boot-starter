package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.test.MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.UsersGetProfileRequest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("UserGetProfileMethod")
class UsersGetProfileUnitTest() {

    @DisplayName("Test User.getProfile method")
    @Test
    fun userListTest() {
        val successFunction: (SuccessfulUsersGetProfileResponse?) -> Any = mock {}
        val failureFunction: (ErrorUsersGetProfileResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.users().getProfile("") },
                successFunction, SuccessfulUsersGetProfileResponse.sample(),
                failureFunction, ErrorUsersGetProfileResponse.sample(),
                UsersGetProfileRequest.sample()
        )
    }
}
