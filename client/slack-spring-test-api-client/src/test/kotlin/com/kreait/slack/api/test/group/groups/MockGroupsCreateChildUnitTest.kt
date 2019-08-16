package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsCreateChildResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsCreateChildRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsCreateChildResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Groups.CreateChild Method")
class MockGroupsCreateChildUnitTests {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulGroupsCreateChildResponse?) -> Any = mock { }
        val failureFunction: (ErrorGroupsCreateChildResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.groups().createChild("") },
                successFunction, SuccessfulGroupsCreateChildResponse.sample(),
                failureFunction, ErrorGroupsCreateChildResponse.sample(),
                GroupsCreateChildRequest.sample())
    }
}
