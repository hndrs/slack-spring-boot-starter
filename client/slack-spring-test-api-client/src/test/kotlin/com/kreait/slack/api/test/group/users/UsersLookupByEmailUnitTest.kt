package com.kreait.slack.api.test.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorLookupByEmailResponse
import com.kreait.slack.api.contract.jackson.group.users.LookupByEmailRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulLookupByEmailResponse
import com.kreait.slack.api.contract.jackson.group.users.sample
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
        val successFunction: (SuccessfulLookupByEmailResponse?) -> Any = mock { }
        val failureFunction: (ErrorLookupByEmailResponse?) -> Any = mock { }
        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockClient.users().lookupByEmail("") },
                successFunction, SuccessfulLookupByEmailResponse.sample(),
                failureFunction, ErrorLookupByEmailResponse.sample(),
                LookupByEmailRequest.sample())
    }
}
