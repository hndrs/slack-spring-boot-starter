package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUsergroupsEnableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SlackUsergroupsEnableRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUsergroupsEnableResponse
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
        val successFunction: (SuccessfulUsergroupsEnableResponse?) -> Any = mock { }
        val failureFunction: (ErrorUsergroupsEnableResponse?) -> Any = mock { }
        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockClient.usergroups().enable("") },
                successFunction, SuccessfulUsergroupsEnableResponse.sample(),
                failureFunction, ErrorUsergroupsEnableResponse.sample(),
                SlackUsergroupsEnableRequest.sample())
    }
}
