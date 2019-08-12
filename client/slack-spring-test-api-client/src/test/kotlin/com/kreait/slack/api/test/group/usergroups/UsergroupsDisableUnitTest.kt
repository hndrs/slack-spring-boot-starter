package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorDisableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.DisableRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulDisableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock usergroups.disable Method")
class UsergroupsDisableUnitTest {

    @DisplayName("Mock Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulDisableResponse?) -> Any = mock { }
        val failureFunction: (ErrorDisableResponse?) -> Any = mock { }
        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockClient.usergroups().disable("") },
                successFunction, SuccessfulDisableResponse.sample(),
                failureFunction, ErrorDisableResponse.sample(),
                DisableRequest.sample())
    }
}
