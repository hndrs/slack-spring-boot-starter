package io.olaph.slack.client.test.group.auth

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.auth.AuthRevokeRequest
import io.olaph.slack.dto.jackson.group.auth.ErrorAuthRevokeResponse
import io.olaph.slack.dto.jackson.group.auth.SuccessfulAuthRevokeResponse
import io.olaph.slack.dto.jackson.group.auth.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockAuthTest")
class MockAuthRevokeUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulAuthRevokeResponse?) -> Any = mock {}
        val failureFunction: (ErrorAuthRevokeResponse?) -> Any = mock {}

        MockMethodTestHelper.verify(MockSlackClient().auth().revoke(""),
                successFunction, SuccessfulAuthRevokeResponse.sample(),
                failureFunction, ErrorAuthRevokeResponse(false, ""), AuthRevokeRequest.sample()
        )
    }


}
