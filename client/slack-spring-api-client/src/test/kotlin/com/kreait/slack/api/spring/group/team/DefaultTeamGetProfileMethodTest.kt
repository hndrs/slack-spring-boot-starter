package com.kreait.slack.api.spring.group.team

import com.kreait.slack.api.contract.jackson.group.team.ErrorTeamGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.team.SuccessfulTeamGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.team.TeamGetProfileRequest
import com.kreait.slack.api.contract.jackson.group.team.TeamVisibility
import com.kreait.slack.api.contract.jackson.group.team.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultTeamGetProfileMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("team.identity Failure")
    fun TeamGetProfileFailure() {
        val response = ErrorTeamGetProfileResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "team.profile.get", response)
        val verifier = Verifier(response)

        DefaultTeamGetProfileMethod("", mockTemplate)
                .with(TeamGetProfileRequest(TeamVisibility.ALL))
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("team.identity Success")
    fun TeamGetProfileSuccess() {
        val response = SuccessfulTeamGetProfileResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "team.profile.get", response)
        val verifier = Verifier(response)

        DefaultTeamGetProfileMethod("", mockTemplate)
                .with(TeamGetProfileRequest(TeamVisibility.ALL))
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
