package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsMarkResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsMarkRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsMarkResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Groups.Mark Method")
class MockGroupsMarkUnitTest {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulGroupsMarkResponse?) -> Any = mock { }
        val failureFunction: (ErrorGroupsMarkResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.groups().mark("") },
                successFunction, SuccessfulGroupsMarkResponse.sample(),
                failureFunction, ErrorGroupsMarkResponse.sample(),
                GroupsMarkRequest.sample())
    }
}
