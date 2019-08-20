package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsInfoResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsInfoRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsInfoResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Groups.Info Method")
class MockGroupsInfoUnitTest {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulGroupsInfoResponse?) -> Any = mock { }
        val failureFunction: (ErrorGroupsInfoResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.groups().info("") },
                successFunction, SuccessfulGroupsInfoResponse.sample(),
                failureFunction, ErrorGroupsInfoResponse.sample(),
                GroupsInfoRequest.sample())
    }
}
