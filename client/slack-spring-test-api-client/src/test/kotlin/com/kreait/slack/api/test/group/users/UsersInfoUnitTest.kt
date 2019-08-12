package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersInfoResponse
import com.kreait.slack.api.contract.jackson.group.users.SlackUserInfoRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersInfoResponse
import com.kreait.slack.api.contract.jackson.group.users.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("UserConversationMethod")
class UsersInfoUnitTest() {

    @DisplayName("Test User list method")
    @Test
    fun userListTest() {
        val successFunction: (SuccessfulUsersInfoResponse?) -> Any = mock {}
        val failureFunction: (ErrorUsersInfoResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.users().info("") },
                successFunction, SuccessfulUsersInfoResponse.sample(),
                failureFunction, ErrorUsersInfoResponse.sample(),
                SlackUserInfoRequest.sample()
        )
    }
}
