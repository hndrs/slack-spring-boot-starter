package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsInviteResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsInviteRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsInviteResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Groups.invite Method")
class MockGroupsInviteUnitTest {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulGroupsInviteResponse?) -> Any = mock { }
        val failureFunction: (ErrorGroupsInviteResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.groups().invite("") },
                successFunction, SuccessfulGroupsInviteResponse.sample(),
                failureFunction, ErrorGroupsInviteResponse.sample(),
                GroupsInviteRequest.sample())
    }
}
