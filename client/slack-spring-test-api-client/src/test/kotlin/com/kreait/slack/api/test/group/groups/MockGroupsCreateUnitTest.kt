package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsCreateResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsCreateRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsCreateResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Groups.Create Method")
class MockGroupsCreateUnitTest {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulGroupsCreateResponse?) -> Any = mock { }
        val failureFunction: (ErrorGroupsCreateResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.groups().create("") },
                successFunction, SuccessfulGroupsCreateResponse.sample(),
                failureFunction, ErrorGroupsCreateResponse.sample(),
                GroupsCreateRequest.sample())
    }
}
