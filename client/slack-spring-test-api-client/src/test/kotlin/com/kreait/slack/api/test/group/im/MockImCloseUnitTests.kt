package com.kreait.slack.api.test.group.im

import com.kreait.slack.api.contract.jackson.group.im.ErrorImCloseResponse
import com.kreait.slack.api.contract.jackson.group.im.ImCloseRequest
import com.kreait.slack.api.contract.jackson.group.im.SuccessfulImCloseResponse
import com.kreait.slack.api.contract.jackson.group.im.sample
import com.kreait.slack.api.test.MockMethodTestHelper
import com.kreait.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
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
                ImCloseRequest.sample()
        )
    }
}
