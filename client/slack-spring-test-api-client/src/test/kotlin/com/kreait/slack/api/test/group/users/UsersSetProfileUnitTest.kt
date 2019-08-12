package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorSetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulSetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("UserSetProfileMethod")
class UsersSetProfileUnitTest() {

    @DisplayName("Test Users.setProfile method")
    @Test
    fun userListTest() {
        val successFunction: (SuccessfulSetProfileResponse?) -> Any = mock {}
        val failureFunction: (ErrorSetProfileResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.users().setProfile("") },
                successFunction, SuccessfulSetProfileResponse.sample(),
                failureFunction, ErrorSetProfileResponse.sample(),
                Unit
        )
    }
}
