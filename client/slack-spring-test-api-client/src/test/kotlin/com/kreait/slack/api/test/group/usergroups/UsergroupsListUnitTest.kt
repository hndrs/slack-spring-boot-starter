package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.ListRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock usergroups.list Method")
class UsergroupsListUnitTest {

    @DisplayName("Mock Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulListResponse?) -> Any = mock { }
        val failureFunction: (ErrorListResponse?) -> Any = mock { }
        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockClient.usergroups().list("") },
                successFunction, SuccessfulListResponse.sample(),
                failureFunction, ErrorListResponse.sample(),
                ListRequest.sample())
    }
}
