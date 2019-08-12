package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersDeletePhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersDeletePhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("UsersDeletePhotoMethod")
class UsersDeletePhotoUnitTest() {

    @DisplayName("Test Users.deletePhoto method")
    @Test
    fun userListTest() {
        val successFunction: (SuccessfulUsersDeletePhotoResponse?) -> Any = mock {}
        val failureFunction: (ErrorUsersDeletePhotoResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.users().deletePhoto("") },
                successFunction, SuccessfulUsersDeletePhotoResponse.sample(),
                failureFunction, ErrorUsersDeletePhotoResponse.sample(), Unit
        )
    }
}
