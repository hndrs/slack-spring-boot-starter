package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.test.MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.api.contract.jackson.group.usergroups.users.ErrorUsergroupUsersUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.SlackUsergroupUsersUpdateRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.users.SuccessfulUsergroupUsersUpdateResponse
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
                SlackUsergroupUsersUpdateRequest.sample())
    }
}
