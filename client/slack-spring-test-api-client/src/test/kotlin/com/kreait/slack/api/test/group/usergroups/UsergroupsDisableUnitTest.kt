package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.test.MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUsergroupsDisableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SlackUsergroupsDisableRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUsergroupsDisableResponse
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock usergroups.disable Method")
class UsergroupsDisableUnitTest {

    @DisplayName("Mock Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulUsergroupsDisableResponse?) -> Any = mock { }
        val failureFunction: (ErrorUsergroupsDisableResponse?) -> Any = mock { }
        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockClient.usergroups().disable("") },
                successFunction, SuccessfulUsergroupsDisableResponse.sample(),
                failureFunction, ErrorUsergroupsDisableResponse.sample(),
                SlackUsergroupsDisableRequest.sample())
    }
}
