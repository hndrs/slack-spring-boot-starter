package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.UpdateRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock usergroups.update Method")
class UsergroupsUpdateUnitTest {

    @DisplayName("Mock Successfuk")
    @Test
    fun mockSuccess() {
        val successFunction: (SuccessfulUpdateResponse?) -> Any = mock { }
        val failureFunction: (ErrorUpdateResponse?) -> Any = mock { }
        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockClient.usergroups().update("") },
                successFunction, SuccessfulUpdateResponse.sample(),
                failureFunction, ErrorUpdateResponse.sample(),
                UpdateRequest.sample())
    }
}
