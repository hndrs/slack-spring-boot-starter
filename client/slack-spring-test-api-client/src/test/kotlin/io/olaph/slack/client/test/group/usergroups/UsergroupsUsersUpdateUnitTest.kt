package io.olaph.slack.client.test.group.usergroups

import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.usergroups.users.ErrorUsergroupUsersUpdateResponse
import io.olaph.slack.dto.jackson.group.usergroups.users.SlackUsergroupUsersUpdateRequest
import io.olaph.slack.dto.jackson.group.usergroups.users.SuccessfulUsergroupUsersUpdateResponse
import io.olaph.slack.dto.jackson.group.usergroups.users.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock UsergroupsUsersUpdate Method")
class UsergroupsUsersUpdateUnitTest {

    @DisplayName("Mock Successful")
    @Test
    fun testMockMethod() {

        val successFunction: (SuccessfulUsergroupUsersUpdateResponse?) -> Any = mock {  }
        val failureFunction: (ErrorUsergroupUsersUpdateResponse?) -> Any = mock {  }

        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify( { mockClient.usergroups().usersUpdate("") },
                successFunction, SuccessfulUsergroupUsersUpdateResponse.sample(),
                failureFunction, ErrorUsergroupUsersUpdateResponse.sample(),
                SlackUsergroupUsersUpdateRequest.sample())
    }
}