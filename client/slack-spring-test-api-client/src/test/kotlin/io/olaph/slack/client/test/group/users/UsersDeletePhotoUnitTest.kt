package io.olaph.slack.client.test.group.users

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.users.ErrorUsersDeletePhotoResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersDeletePhotoResponse
import io.olaph.slack.dto.jackson.group.users.sample
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
