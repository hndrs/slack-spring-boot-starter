package io.olaph.slack.client.test.group.auth

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.auth.ErrorAuthTestResponse
import io.olaph.slack.dto.jackson.group.auth.SuccessfulAuthTestResponse
import io.olaph.slack.dto.jackson.group.auth.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockAuthTest")
class MockAuthTestUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulAuthTestResponse?) -> Any = mock {}
        val failureFunction: (ErrorAuthTestResponse?) -> Any = mock {}

        MockMethodTestHelper.verify(MockSlackClient().auth().test(""),
                successFunction, SuccessfulAuthTestResponse.sample(),
                failureFunction, ErrorAuthTestResponse(false, ""), Unit
        )
    }


}
