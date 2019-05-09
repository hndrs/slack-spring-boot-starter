package io.olaph.slack.client.test.group.im

import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.im.ErrorImCloseResponse
import io.olaph.slack.dto.jackson.group.im.SlackImCloseRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImCloseResponse
import io.olaph.slack.dto.jackson.group.im.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock ImClose Method")
class MockImCloseUnitTests {

    @DisplayName("Mocking Successful")
    @Test
    fun imCloseMockSuccess() {

        val successfunction: (SuccessfulImCloseResponse?) -> Any = mock { }
        val failureFunction: (ErrorImCloseResponse?) -> Any = mock { }

        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockClient.im().close("") },
                successfunction, SuccessfulImCloseResponse.sample(),
                failureFunction, ErrorImCloseResponse.sample(),
                SlackImCloseRequest.sample()
        )
    }
}