package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorSetPhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulSetPhotoResponse
import com.kreait.slack.api.contract.jackson.group.users.SetPhotoRequest
import com.kreait.slack.api.contract.jackson.group.users.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("users.setPhoto Method")
class UsersSetPhotoUnitTest {

    @DisplayName("MockMethod users.setPhoto successful")
    @Test
    fun usersSetPhotoTest() {

        val successFunction: (SuccessfulSetPhotoResponse?) -> Any = mock { }
        val failureFunction: (ErrorSetPhotoResponse?) -> Any = mock { }

        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockClient.users().setPhoto("") },
                successFunction, SuccessfulSetPhotoResponse.sample(),
                failureFunction, ErrorSetPhotoResponse.sample(),
                SetPhotoRequest(mock { }, 0, 0, 0))
    }
}
