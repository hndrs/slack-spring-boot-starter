package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsListResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsListRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsListResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Groups.List Method")
class MockGroupsListUnitTest {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulGroupsListResponse?) -> Any = mock { }
        val failureFunction: (ErrorGroupsListResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.groups().list("") },
                successFunction, SuccessfulGroupsListResponse.sample(),
                failureFunction, ErrorGroupsListResponse.sample(),
                GroupsListRequest.sample())
    }
}
