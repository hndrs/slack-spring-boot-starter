package io.hndrs.slack.api.spring.group.auth

import io.hndrs.slack.api.contract.jackson.group.auth.AuthRevokeRequest
import io.hndrs.slack.api.contract.jackson.group.auth.ErrorAuthRevokeResponse
import io.hndrs.slack.api.contract.jackson.group.auth.ErrorAuthTestResponse
import io.hndrs.slack.api.contract.jackson.group.auth.SuccessfulAuthRevokeResponse
import io.hndrs.slack.api.contract.jackson.group.auth.SuccessfulAuthTestResponse
import io.hndrs.slack.api.contract.jackson.group.auth.sample
import io.hndrs.slack.api.spring.DynamicGroupTests
import io.hndrs.slack.api.spring.MetaInfo
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.springframework.web.client.RestTemplate

@DisplayName("Auth Methods Tests")
class MethodsTests {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
    }

    @TestFactory
    fun `methodInvocations`(): List<DynamicTest> = DynamicGroupTests.methodInvocations(testCases = testCases(), mockTemplate = mockTemplate)

    private fun testCases() = listOf(
            MetaInfo("auth.revoke", SuccessfulAuthRevokeResponse.sample(), ErrorAuthRevokeResponse.sample(), AuthRevokeRequest.sample(), SpringRevokeMethod("", mockTemplate)),
            MetaInfo("auth.test", SuccessfulAuthTestResponse.sample(), ErrorAuthTestResponse.sample(), "", SpringTestMethod("", mockTemplate))
    )
}
