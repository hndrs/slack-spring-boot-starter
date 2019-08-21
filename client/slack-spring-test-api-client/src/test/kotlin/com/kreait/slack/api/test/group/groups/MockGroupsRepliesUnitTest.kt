package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsRepliesResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsRepliesRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsRepliesResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Groups.Replies Method")
class MockGroupsRepliesUnitTest {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulGroupsRepliesResponse?) -> Any = mock { }
        val failureFunction: (ErrorGroupsRepliesResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.groups().replies("") },
                successFunction, SuccessfulGroupsRepliesResponse.sample(),
                failureFunction, ErrorGroupsRepliesResponse.sample(),
                GroupsRepliesRequest.sample())
    }
}
