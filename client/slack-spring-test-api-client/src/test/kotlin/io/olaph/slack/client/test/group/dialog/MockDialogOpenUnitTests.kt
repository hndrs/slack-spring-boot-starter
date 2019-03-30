package io.olaph.slack.client.test.group.dialog

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.dialog.ErrorOpenDialogResponse
import io.olaph.slack.dto.jackson.group.dialog.SlackOpenDialogRequest
import io.olaph.slack.dto.jackson.group.dialog.SuccessfulOpenDialogResponse
import io.olaph.slack.dto.jackson.group.dialog.sample
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
