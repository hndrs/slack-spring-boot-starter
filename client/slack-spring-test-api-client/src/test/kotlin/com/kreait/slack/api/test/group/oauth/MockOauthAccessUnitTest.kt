package com.kreait.slack.api.test.group.oauth

import com.kreait.slack.api.contract.jackson.group.oauth.ErrorOauthAccessResponse
import com.kreait.slack.api.contract.jackson.group.oauth.OauthAccessRequest
import com.kreait.slack.api.contract.jackson.group.oauth.SuccessFullOauthAccessResponse
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
        val successFunction: (SuccessFullOauthAccessResponse?) -> Any = mock {}
        val failureFunction: (ErrorOauthAccessResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.oauth().access() },
                successFunction, SuccessFullOauthAccessResponse.sample(),
                failureFunction, ErrorOauthAccessResponse.sample(),
                OauthAccessRequest.sample()
        )
    }
}
