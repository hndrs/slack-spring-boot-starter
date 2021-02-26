package io.hndrs.slack.api.test.group.auth

import io.hndrs.slack.api.contract.jackson.group.auth.AuthRevokeRequest
import io.hndrs.slack.api.contract.jackson.group.auth.ErrorAuthRevokeResponse
import io.hndrs.slack.api.contract.jackson.group.auth.ErrorAuthTestResponse
import io.hndrs.slack.api.contract.jackson.group.auth.SuccessfulAuthRevokeResponse
import io.hndrs.slack.api.contract.jackson.group.auth.SuccessfulAuthTestResponse
import io.hndrs.slack.api.contract.jackson.group.auth.sample
import io.hndrs.slack.api.test.DynamicMockGroupTests
import io.hndrs.slack.api.test.MockMetaInfo
import io.hndrs.slack.api.test.MockSlackClient
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class DynamicMockTests {

    @TestFactory
    fun methodInvocations(): List<DynamicTest> = DynamicMockGroupTests.methodInvocations(testCases = testCases())

    private val client = MockSlackClient()

    private fun testCases() = listOf(
            MockMetaInfo({ client.auth().test("") }, mock { }, SuccessfulAuthTestResponse.sample(), mock { }, ErrorAuthTestResponse.sample(), Unit),
            MockMetaInfo({ client.auth().revoke("") }, mock { }, SuccessfulAuthRevokeResponse.sample(), mock { }, ErrorAuthRevokeResponse.sample(), AuthRevokeRequest.sample())
    )
}
