package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.GetProfileRequest
import com.kreait.slack.api.contract.jackson.group.users.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("UserGetProfileMethod")
class UsersGetProfileUnitTest() {

    @DisplayName("Test User.getProfile method")
    @Test
    fun userListTest() {
        val successFunction: (SuccessfulGetProfileResponse?) -> Any = mock {}
        val failureFunction: (ErrorGetProfileResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.users().getProfile("") },
                successFunction, SuccessfulGetProfileResponse.sample(),
                failureFunction, ErrorGetProfileResponse.sample(),
                GetProfileRequest.sample()
        )
    }
}
