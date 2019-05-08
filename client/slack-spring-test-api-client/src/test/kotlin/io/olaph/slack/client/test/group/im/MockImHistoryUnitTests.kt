package io.olaph.slack.client.test.group.im

import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.im.ErrorImHistoryResponse
import io.olaph.slack.dto.jackson.group.im.SlackImHistoryRequest
import io.olaph.slack.dto.jackson.group.im.SuccessfulImHistoryResponse
import io.olaph.slack.dto.jackson.group.im.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Mock im.history Method Test")
class MockImHistoryUnitTests {

    @DisplayName("Mocking Successful")
    @Test
    fun imHistoryMockSuccess() {

        val successFunction: (SuccessfulImHistoryResponse?) -> Any = mock {  }
        val failureFunction: (ErrorImHistoryResponse?) -> Any = mock {  }

        val mockClient = MockSlackClient()

        MockMethodTestHelper.verify( { mockClient.im().history("") },
                successFunction, SuccessfulImHistoryResponse.sample(),
                failureFunction, ErrorImHistoryResponse.sample(),
                SlackImHistoryRequest.sample()
        )
    }
}