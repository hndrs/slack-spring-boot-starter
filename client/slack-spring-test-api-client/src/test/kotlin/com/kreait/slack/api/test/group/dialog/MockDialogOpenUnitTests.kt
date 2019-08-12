package com.kreait.slack.api.test.group.dialog

import com.kreait.slack.api.test.MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import com.kreait.slack.api.test.MockSlackClient
import com.kreait.slack.api.contract.jackson.group.dialog.ErrorOpenDialogResponse
import com.kreait.slack.api.contract.jackson.group.dialog.SlackOpenDialogRequest
import com.kreait.slack.api.contract.jackson.group.dialog.SuccessfulOpenDialogResponse
import com.kreait.slack.api.contract.jackson.group.dialog.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockDialogOpen")
class MockDialogOpenUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (SuccessfulOpenDialogResponse?) -> Any = mock {}
        val failureFunction: (ErrorOpenDialogResponse?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.dialog().open("") },
                successFunction, SuccessfulOpenDialogResponse.sample(),
                failureFunction, ErrorOpenDialogResponse.sample(), SlackOpenDialogRequest.sample()
        )
    }


}
