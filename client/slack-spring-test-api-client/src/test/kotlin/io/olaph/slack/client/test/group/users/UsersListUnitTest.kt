package io.olaph.slack.client.test.group.users

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.dto.jackson.group.users.ErrorUserListResponse
import io.olaph.slack.dto.jackson.group.users.SlackUserListRequest
import io.olaph.slack.dto.jackson.group.users.SuccessfulUserListResponse
import io.olaph.slack.dto.jackson.group.users.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("UsersList Method")
class UsersListUnitTest {

    @DisplayName("Test Users.list method")
    @Test
    fun testUsersList() {
        val successFunction: (SuccessfulUserListResponse?) -> Any = mock {}
        val failureFunction: (ErrorUserListResponse?) -> Any = mock {}

        MockMethodTestHelper.verify(MockUserListMethod(),
                successFunction, SuccessfulUserListResponse.sample(),
                failureFunction, ErrorUserListResponse.sample(),
                SlackUserListRequest()
        )
    }
}