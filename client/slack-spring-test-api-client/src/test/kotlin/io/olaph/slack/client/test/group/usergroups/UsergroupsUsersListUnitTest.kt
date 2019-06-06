package io.olaph.slack.client.test.group.usergroups

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.usergroups.users.ErrorUsergroupsUsersListResponse
import io.olaph.slack.dto.jackson.group.usergroups.users.SlackUsergroupsUsersListRequest
import io.olaph.slack.dto.jackson.group.usergroups.users.SuccessfulUsergroupsUsersListResponse
import io.olaph.slack.dto.jackson.group.usergroups.users.sample
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