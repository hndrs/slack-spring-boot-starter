package io.olaph.slack.client.test.group.users

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.users.ErrorUsersSetPhotoResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersSetPhotoResponse
import io.olaph.slack.dto.jackson.group.users.UsersSetPhotoRequest
import io.olaph.slack.dto.jackson.group.users.sample
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