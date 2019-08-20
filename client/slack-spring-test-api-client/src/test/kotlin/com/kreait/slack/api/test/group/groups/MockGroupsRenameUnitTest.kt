package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsRenameResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsRenameRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsRenameResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Groups.Rename Method")
class MockGroupsRenameUnitTest {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulGroupsRenameResponse?) -> Any = mock { }
        val failureFunction: (ErrorGroupsRenameResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.groups().rename("") },
                successFunction, SuccessfulGroupsRenameResponse.sample(),
                failureFunction, ErrorGroupsRenameResponse.sample(),
                GroupsRenameRequest.sample())
    }
}
