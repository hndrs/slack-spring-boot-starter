package io.olaph.slack.client.test.group.im

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.im.ErrorImListResponse
import io.olaph.slack.dto.jackson.group.im.SlackImListRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImListResponse
import io.olaph.slack.dto.jackson.group.im.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockImListMethod")
class MockImListUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulImListResponse?) -> Any = mock {}
        val failureFunction: (ErrorImListResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.im().list("") },
                successFunction, SuccessfulImListResponse.sample(),
                failureFunction, ErrorImListResponse.sample(),
                SlackImListRequest.sample()
        )
    }
}
