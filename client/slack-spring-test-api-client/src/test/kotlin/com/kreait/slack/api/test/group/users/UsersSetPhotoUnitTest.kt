package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.test.MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersSetPhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersSetPhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.UsersSetPhotoRequest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("users.setPhoto Method")
class UsersSetPhotoUnitTest {

    @DisplayName("MockMethod users.setPhoto successful")
    @Test
    fun usersSetPhotoTest() {

        val successFunction: (SuccessfulUsersSetPhotoResponse?) -> Any = mock { }
        val failureFunction: (ErrorUsersSetPhotoResponse?) -> Any = mock { }

        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockClient.users().setPhoto("") },
                successFunction, SuccessfulUsersSetPhotoResponse.sample(),
                failureFunction, ErrorUsersSetPhotoResponse.sample(),
                UsersSetPhotoRequest(mock { }, 0, 0, 0))
    }
}
