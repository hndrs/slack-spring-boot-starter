package com.kreait.slack.api.test.group.auth

import com.kreait.slack.api.test.MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.api.contract.jackson.group.auth.ErrorAuthTestResponse
import com.kreait.slack.api.contract.jackson.group.auth.SuccessfulAuthTestResponse
import com.kreait.slack.api.contract.jackson.group.auth.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockAuthTest")
class MockAuthTestUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulAuthTestResponse?) -> Any = mock {}
        val failureFunction: (ErrorAuthTestResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.auth().test("") },
                successFunction, SuccessfulAuthTestResponse.sample(),
                failureFunction, ErrorAuthTestResponse.sample(), Unit
        )
    }


}
