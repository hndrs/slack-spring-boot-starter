package com.kreait.slack.api.spring.group.team

import com.kreait.slack.api.contract.jackson.group.team.ErrorProfileResponse
import com.kreait.slack.api.contract.jackson.group.team.SuccessfulProfileResponse
import com.kreait.slack.api.contract.jackson.group.team.ProfileRequest
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
        val response = ErrorProfileResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "team.profile.get", response)
        val verifier = Verifier(response)

        DefaultTeamGetProfileMethod("", mockTemplate)
                .with(ProfileRequest(TeamVisibility.ALL))
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("team.identity Success")
    fun TeamGetProfileSuccess() {
        val response = SuccessfulProfileResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "team.profile.get", response)
        val verifier = Verifier(response)

        DefaultTeamGetProfileMethod("", mockTemplate)
                .with(ProfileRequest(TeamVisibility.ALL))
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
