package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUsergroupsCreateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SlackUsergroupsCreateRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUsergroupsCreateResponse
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock UsergroupsCreate Method")
class UsergroupsCreateUnitTest {

    @DisplayName("Mock Successful")
    @Test
    fun testMockMethod() {

        val successFunction: (SuccessfulUsergroupsCreateResponse?) -> Any = mock {  }
        val failureFunction: (ErrorUsergroupsCreateResponse?) -> Any = mock {  }

        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify( { mockClient.usergroups().create("") },
                successFunction, SuccessfulUsergroupsCreateResponse.sample(),
                failureFunction, ErrorUsergroupsCreateResponse.sample(),
                SlackUsergroupsCreateRequest.sample())
    }
}
