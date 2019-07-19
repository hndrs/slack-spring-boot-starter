package io.olaph.slack.client.spring.group.usergroups

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsListResponse
import io.olaph.slack.dto.jackson.group.usergroups.SlackUsergroupsListRequest
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsListResponse
import io.olaph.slack.dto.jackson.group.usergroups.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class UsergroupsListMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @DisplayName("usergroups.list Method Success")
    @Test
    fun usergroupsListMethodSuccess() {
        val response = SuccessfulUsergroupsListResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "usergroups.list", response)
        val verifier = Verifier(response)

        DefaultUsergroupsListMethod("", mockTemplate)
                .with(SlackUsergroupsListRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()

        verifier.verify()
        mockServer.verify()
    }

    @DisplayName("usergroups.list Method Failure")
    @Test
    fun usergroupsListMethodFailure() {
        val response = ErrorUsergroupsListResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "usergroups.list", response)
        val verifier = Verifier(response)

        DefaultUsergroupsListMethod("", mockTemplate)
                .with(SlackUsergroupsListRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()

        verifier.verify()
        mockServer.verify()
    }
}