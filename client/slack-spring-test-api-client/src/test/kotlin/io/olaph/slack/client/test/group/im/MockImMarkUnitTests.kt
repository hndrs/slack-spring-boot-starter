package io.olaph.slack.client.test.group.im

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.im.ErrorImMarkResponse
import io.olaph.slack.dto.jackson.group.im.SlackImMarkRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImMarkResponse
import io.olaph.slack.dto.jackson.group.im.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockImOpenMethod")
class MockImMarkUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulImMarkResponse?) -> Any = mock {}
        val failureFunction: (ErrorImMarkResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.im().mark("") },
                successFunction, SuccessfulImMarkResponse.sample(),
                failureFunction, ErrorImMarkResponse.sample(),
                SlackImMarkRequest.sample()
        )
    }
}
