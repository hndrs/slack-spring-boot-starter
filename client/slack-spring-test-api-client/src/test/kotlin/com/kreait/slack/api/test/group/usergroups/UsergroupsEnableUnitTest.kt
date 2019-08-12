package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorEnableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.EnableRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulEnableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock usergroups.enable Method")
class UsergroupsEnableUnitTest {

    @DisplayName("Mock Successful")
    @Test
    fun mockUsergroupsEnable() {
        val successFunction: (SuccessfulEnableResponse?) -> Any = mock { }
        val failureFunction: (ErrorEnableResponse?) -> Any = mock { }
        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockClient.usergroups().enable("") },
                successFunction, SuccessfulEnableResponse.sample(),
                failureFunction, ErrorEnableResponse.sample(),
                EnableRequest.sample())
    }
}
