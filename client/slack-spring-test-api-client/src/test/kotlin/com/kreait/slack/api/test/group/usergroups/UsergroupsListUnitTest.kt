package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.test.MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUsergroupsListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SlackUsergroupsListRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUsergroupsListResponse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock usergroups.list Method")
class UsergroupsListUnitTest {

    @DisplayName("Mock Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulUsergroupsListResponse?) -> Any = mock { }
        val failureFunction: (ErrorUsergroupsListResponse?) -> Any = mock { }
        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockClient.usergroups().list("") },
                successFunction, SuccessfulUsergroupsListResponse.sample(),
                failureFunction, ErrorUsergroupsListResponse.sample(),
                SlackUsergroupsListRequest.sample())
    }
}
