package com.kreait.slack.api.test.group.oauth

import com.kreait.slack.api.contract.jackson.group.oauth.ErrorAccessResponse
import com.kreait.slack.api.contract.jackson.group.oauth.AccessRequest
import com.kreait.slack.api.contract.jackson.group.oauth.SuccessfullAccessResponse
import com.kreait.slack.api.contract.jackson.group.oauth.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MockOauthAccessUnitTest {

    @DisplayName("Test Oauth Access")
    @Test
    fun testOauthAccess() {
        val successFunction: (SuccessfullAccessResponse?) -> Any = mock {}
        val failureFunction: (ErrorAccessResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.oauth().access() },
                successFunction, SuccessfullAccessResponse.sample(),
                failureFunction, ErrorAccessResponse.sample(),
                AccessRequest.sample()
        )
    }
}
