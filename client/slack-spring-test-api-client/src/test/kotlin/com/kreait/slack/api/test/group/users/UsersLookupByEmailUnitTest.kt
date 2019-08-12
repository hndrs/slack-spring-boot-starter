package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersLookupByEmailResponse
import com.kreait.slack.api.contract.jackson.group.users.SlackUsersLookupByEmailRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersLookupByEmailResponse
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("users.lookupByEmail Method")
class UsersLookupByEmailUnitTest {

    @DisplayName("users.lookupByEmail Test")
    @Test
    fun usersLookupByEmailTest() {
        val successFunction: (SuccessfulUsersLookupByEmailResponse?) -> Any = mock { }
        val failureFunction: (ErrorUsersLookupByEmailResponse?) -> Any = mock { }
        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockClient.users().lookupByEmail("") },
                successFunction, SuccessfulUsersLookupByEmailResponse.sample(),
                failureFunction, ErrorUsersLookupByEmailResponse.sample(),
                SlackUsersLookupByEmailRequest.sample())
    }
}
