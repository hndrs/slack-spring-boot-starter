package io.olaph.slack.client.test.group.users

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.users.ErrorUsersSetProfileResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersSetProfileResponse
import io.olaph.slack.dto.jackson.group.users.sample
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
