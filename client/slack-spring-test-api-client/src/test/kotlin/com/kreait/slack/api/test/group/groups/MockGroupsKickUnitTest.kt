package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsKickResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsKickRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsKickResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Groups.Kick Method")
class MockGroupsKickUnitTest {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulGroupsKickResponse?) -> Any = mock { }
        val failureFunction: (ErrorGroupsKickResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.groups().kick("") },
                successFunction, SuccessfulGroupsKickResponse.sample(),
                failureFunction, ErrorGroupsKickResponse.sample(),
                GroupsKickRequest.sample())
    }
}
