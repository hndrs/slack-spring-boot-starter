package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.users.ErrorUsergroupsUsersListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.SlackUsergroupsUsersListRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.users.SuccessfulUsergroupsUsersListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock usergroups.users.list Method")
class UsergroupsUsersListUnitTest {

    @DisplayName("Mock Successful")
    @Test
    fun usergroupsUsersListMockTest() {
        val successFunction: (SuccessfulUsergroupsUsersListResponse?) -> Any = mock { }
        val failureFunction: (ErrorUsergroupsUsersListResponse?) -> Any = mock { }
        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockClient.usergroups().usersList("") },
                successFunction, SuccessfulUsergroupsUsersListResponse.sample(),
                failureFunction, ErrorUsergroupsUsersListResponse.sample(),
                SlackUsergroupsUsersListRequest.sample())
    }
}
