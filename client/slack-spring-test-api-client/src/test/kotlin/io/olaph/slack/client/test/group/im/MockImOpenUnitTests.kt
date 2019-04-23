package io.olaph.slack.client.test.group.im

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.im.ErrorImOpenResponse
import io.olaph.slack.dto.jackson.group.im.SlackImOpenRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImOpenResponse
import io.olaph.slack.dto.jackson.group.im.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockImOpenMethod")
class MockImOpenUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulImOpenResponse?) -> Any = mock {}
        val failureFunction: (ErrorImOpenResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.im().open("") },
                successFunction, SuccessfulImOpenResponse.sample(),
                failureFunction, ErrorImOpenResponse.sample(),
                SlackImOpenRequest.sample()
        )
    }
}
