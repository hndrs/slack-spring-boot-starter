package io.hndrs.slack.api.spring.group.team

import io.hndrs.slack.api.contract.jackson.group.team.ErrorProfileResponse
import io.hndrs.slack.api.contract.jackson.group.team.ProfileRequest
import io.hndrs.slack.api.contract.jackson.group.team.SuccessfulProfileResponse
import io.hndrs.slack.api.contract.jackson.group.team.sample
import io.hndrs.slack.api.spring.DynamicGroupTests
import io.hndrs.slack.api.spring.MetaInfo
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.springframework.web.client.RestTemplate

@DisplayName("Team Tests")
class MethodTests {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
    }

    @TestFactory
    @DisplayName("Method Invocation Tests")
    fun methodInvocations(): List<DynamicTest> = DynamicGroupTests.methodInvocations(testCases = testCases(), mockTemplate = mockTemplate)

    private fun testCases() = listOf(
            MetaInfo("team.profile.get", SuccessfulProfileResponse.sample(), ErrorProfileResponse.sample(), ProfileRequest.sample(), SpringTeamGetProfileMethod("", mockTemplate))
    )
}
