package com.kreait.slack.api.test.group.auth

import com.kreait.slack.api.contract.jackson.group.auth.AuthRevokeRequest
import com.kreait.slack.api.contract.jackson.group.auth.ErrorAuthRevokeResponse
import com.kreait.slack.api.contract.jackson.group.auth.SuccessfulAuthRevokeResponse
import com.kreait.slack.api.contract.jackson.group.auth.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockAuthTest")
class MockAuthRevokeUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulAuthRevokeResponse?) -> Any = mock {}
        val failureFunction: (ErrorAuthRevokeResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.auth().revoke("") },
                successFunction, SuccessfulAuthRevokeResponse.sample(),
                failureFunction, ErrorAuthRevokeResponse(false, ""), AuthRevokeRequest.sample()
        )
    }


}
