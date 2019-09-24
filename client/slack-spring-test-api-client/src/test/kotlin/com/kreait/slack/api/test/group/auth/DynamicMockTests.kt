package com.kreait.slack.api.test.group.auth

import com.kreait.slack.api.contract.jackson.group.auth.AuthRevokeRequest
import com.kreait.slack.api.contract.jackson.group.auth.ErrorAuthRevokeResponse
import com.kreait.slack.api.contract.jackson.group.auth.ErrorAuthTestResponse
import com.kreait.slack.api.contract.jackson.group.auth.SuccessfulAuthRevokeResponse
import com.kreait.slack.api.contract.jackson.group.auth.SuccessfulAuthTestResponse
import com.kreait.slack.api.contract.jackson.group.auth.sample
import com.kreait.slack.api.test.DynamicMockGroupTests
import com.kreait.slack.api.test.MockMetaInfo
import com.kreait.slack.api.test.MockSlackClient
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
