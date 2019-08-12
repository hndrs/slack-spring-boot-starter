package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorInfoResponse
import com.kreait.slack.api.contract.jackson.group.users.InfoRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulInfoResponse
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
        val successFunction: (SuccessfulInfoResponse?) -> Any = mock {}
        val failureFunction: (ErrorInfoResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.users().info("") },
                successFunction, SuccessfulInfoResponse.sample(),
                failureFunction, ErrorInfoResponse.sample(),
                InfoRequest.sample()
        )
    }
}
