package com.kreait.slack.api.test.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorCreateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.CreateRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulCreateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.sample
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

        val successFunction: (SuccessfulCreateResponse?) -> Any = mock {  }
        val failureFunction: (ErrorCreateResponse?) -> Any = mock {  }

        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify( { mockClient.usergroups().create("") },
                successFunction, SuccessfulCreateResponse.sample(),
                failureFunction, ErrorCreateResponse.sample(),
                CreateRequest.sample())
    }
}
