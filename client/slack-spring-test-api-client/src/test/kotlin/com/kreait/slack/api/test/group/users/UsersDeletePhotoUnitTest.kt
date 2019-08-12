package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorDeletePhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulDeletePhotoResponse
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
        val successFunction: (SuccessfulDeletePhotoResponse?) -> Any = mock {}
        val failureFunction: (ErrorDeletePhotoResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.users().deletePhoto("") },
                successFunction, SuccessfulDeletePhotoResponse.sample(),
                failureFunction, ErrorDeletePhotoResponse.sample(), Unit
        )
    }
}
