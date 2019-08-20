package com.kreait.slack.api.test.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsSetTopicRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Groups.SetTopic Method")
class MockGroupsSetTopicUnitTest {

    @DisplayName("Mocking Success")
    @Test
    fun testMock() {
        val successFunction: (SuccessfulGroupsSetTopicResponse?) -> Any = mock { }
        val failureFunction: (ErrorGroupsSetTopicResponse?) -> Any = mock { }
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.groups().setTopic("") },
                successFunction, SuccessfulGroupsSetTopicResponse.sample(),
                failureFunction, ErrorGroupsSetTopicResponse.sample(),
                GroupsSetTopicRequest.sample())
    }
}
