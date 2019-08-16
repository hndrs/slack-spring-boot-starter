package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsArchiveResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsArchiveRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsArchiveResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Groups.archive Method")
class MockGroupsArchiveUnitTest {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulGroupsArchiveResponse?) -> Any = mock { }
        val failureFunction: (ErrorGroupsArchiveResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.groups().archive("") },
                successFunction, SuccessfulGroupsArchiveResponse.sample(),
                failureFunction, ErrorGroupsArchiveResponse.sample(),
                GroupsArchiveRequest.sample())
    }
}
