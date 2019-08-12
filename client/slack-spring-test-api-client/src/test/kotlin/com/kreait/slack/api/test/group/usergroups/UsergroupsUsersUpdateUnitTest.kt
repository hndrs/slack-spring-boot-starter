package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.users.ErrorUsergroupUsersUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.UsergroupUsersUpdateRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.users.SuccessfulUsergroupUsersUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock UsergroupsUsersUpdate Method")
class UsergroupsUsersUpdateUnitTest {

    @DisplayName("Mock Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulUsergroupUsersUpdateResponse?) -> Any = mock { }
        val failureFunction: (ErrorUsergroupUsersUpdateResponse?) -> Any = mock { }
        val mockClient = MockSlackClient()
        
        MockMethodTestHelper.verify({ mockClient.usergroups().usersUpdate("") },
                successFunction, SuccessfulUsergroupUsersUpdateResponse.sample(),
                failureFunction, ErrorUsergroupUsersUpdateResponse.sample(),
                UsergroupUsersUpdateRequest.sample())
    }
}
