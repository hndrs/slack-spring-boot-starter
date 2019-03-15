package io.olaph.slack.client.test.group.users

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.dto.jackson.group.users.ErrorUsersInfoResponse
import io.olaph.slack.dto.jackson.group.users.SlackUserInfoRequest
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersInfoResponse
import io.olaph.slack.dto.jackson.group.users.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("UserConversationMethod")
class UsersInfoUnitTest() {

    @DisplayName("Test User list method")
    @Test
    fun userListTest() {
        val successFunction: (SuccessfulUsersInfoResponse?) -> Any = mock {}
        val failureFunction: (ErrorUsersInfoResponse?) -> Any = mock {}

        MockMethodTestHelper.verify(MockUsersInfoMethod(),
                successFunction, SuccessfulUsersInfoResponse.sample(),
                failureFunction, ErrorUsersInfoResponse.sample(),
                SlackUserInfoRequest.sample()
        )
    }
}