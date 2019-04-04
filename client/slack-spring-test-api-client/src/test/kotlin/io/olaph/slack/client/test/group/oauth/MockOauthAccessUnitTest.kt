package io.olaph.slack.client.test.group.oauth

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.oauth.ErrorOauthAccessResponse
import io.olaph.slack.dto.jackson.group.oauth.OauthAccessRequest
import io.olaph.slack.dto.jackson.group.oauth.SuccessFullOauthAccessResponse
import io.olaph.slack.dto.jackson.group.oauth.sample
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