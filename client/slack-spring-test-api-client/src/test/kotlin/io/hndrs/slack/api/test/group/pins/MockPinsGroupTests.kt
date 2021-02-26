package io.hndrs.slack.api.test.group.pins

import io.hndrs.slack.api.contract.jackson.group.pins.ErrorPinsAddResponse
import io.hndrs.slack.api.contract.jackson.group.pins.ErrorPinsListResponse
import io.hndrs.slack.api.contract.jackson.group.pins.ErrorPinsRemoveResponse
import io.hndrs.slack.api.contract.jackson.group.pins.PinsAddRequest
import io.hndrs.slack.api.contract.jackson.group.pins.PinsListRequest
import io.hndrs.slack.api.contract.jackson.group.pins.PinsRemoveRequest
import io.hndrs.slack.api.contract.jackson.group.pins.SuccessfulPinsAddResponse
import io.hndrs.slack.api.contract.jackson.group.pins.SuccessfulPinsListResponse
import io.hndrs.slack.api.contract.jackson.group.pins.SuccessfulPinsRemoveResponse
import io.hndrs.slack.api.contract.jackson.group.pins.sample
import io.hndrs.slack.api.test.DynamicMockGroupTests
import io.hndrs.slack.api.test.MockMetaInfo
import io.hndrs.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class MockPinsGroupTests {

    @TestFactory
    fun methodInvocations(): List<DynamicTest> = DynamicMockGroupTests.methodInvocations(testCases = testCases())

    private val client = MockSlackClient()

    private fun testCases() = listOf(
            MockMetaInfo({ client.pins().add("") }, mock { }, SuccessfulPinsAddResponse.sample(), mock { }, ErrorPinsAddResponse.sample(), PinsAddRequest.sample()),
            MockMetaInfo({ client.pins().list("") }, mock {}, SuccessfulPinsListResponse.sample(), mock {}, ErrorPinsListResponse.sample(), PinsListRequest.sample()),
            MockMetaInfo({ client.pins().remove("") }, mock {}, SuccessfulPinsRemoveResponse.sample(), mock {}, ErrorPinsRemoveResponse.sample(), PinsRemoveRequest.sample())
    )
}
