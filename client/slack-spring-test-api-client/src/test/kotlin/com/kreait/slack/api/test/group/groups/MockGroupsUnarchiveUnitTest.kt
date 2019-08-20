package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsUnarchiveRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Groups.Unarchive Method")
class MockGroupsUnarchiveUnitTest {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulGroupsUnarchiveResponse?) -> Any = mock { }
        val failureFunction: (ErrorGroupsUnarchiveResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.groups().unarchive("") },
                successFunction, SuccessfulGroupsUnarchiveResponse.sample(),
                failureFunction, ErrorGroupsUnarchiveResponse.sample(),
                GroupsUnarchiveRequest.sample())
    }
}
