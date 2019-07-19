package io.olaph.slack.client.spring.group.team

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.team.ErrorTeamGetProfileResponse
import io.olaph.slack.dto.jackson.group.team.SuccessfulTeamGetProfileResponse
import io.olaph.slack.dto.jackson.group.team.TeamGetProfileRequest
import io.olaph.slack.dto.jackson.group.team.TeamVisibility
import io.olaph.slack.dto.jackson.group.team.sample
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
