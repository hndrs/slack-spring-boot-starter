package io.olaph.slack.client.test.group.users

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.users.ErrorUsersGetProfileResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersGetProfileResponse
import io.olaph.slack.dto.jackson.group.users.UsersGetProfileRequest
import io.olaph.slack.dto.jackson.group.users.sample
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
