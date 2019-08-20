package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsOpenResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsOpenRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsOpenResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Groups.Open Method")
class MockGroupsOpenUnitTest {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulGroupsOpenResponse?) -> Any = mock { }
        val failureFunction: (ErrorGroupsOpenResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.groups().open("") },
                successFunction, SuccessfulGroupsOpenResponse.sample(),
                failureFunction, ErrorGroupsOpenResponse.sample(),
                GroupsOpenRequest.sample())
    }
}
