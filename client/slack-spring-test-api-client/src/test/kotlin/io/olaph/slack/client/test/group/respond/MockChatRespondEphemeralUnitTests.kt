package io.olaph.slack.client.test.group.respond

import MockMethodTestHelper
import com.nhaarman.mockitokotlin2.mock
import io.olaph.slack.client.test.MockSlackClient
import io.olaph.slack.dto.jackson.group.respond.SlackRespondMessageRequest
import io.olaph.slack.dto.jackson.group.respond.sample
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


@DisplayName("MockRespondMessageMethod")
class MockChatRespondEphemeralUnitTests {


    @DisplayName("Mocking Successful")
    @Test
    fun testMockMethod() {
        val successFunction: (Unit?) -> Any = mock {}
        val failureFunction: (Unit?) -> Any = mock {}
        val mockSlackClient = MockSlackClient()

        MockMethodTestHelper.verify({ mockSlackClient.respond().message("") },
                successFunction, Unit,
                failureFunction, Unit,
                SlackRespondMessageRequest.sample()
        )
    }


}
