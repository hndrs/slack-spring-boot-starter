package io.olaph.slack.client.spring.group.usergroups

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsEnableResponse
import io.olaph.slack.dto.jackson.group.usergroups.SlackUsergroupsEnableRequest
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsEnableResponse
import io.olaph.slack.dto.jackson.group.usergroups.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class UsergroupsEnableMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @DisplayName("usergroups.enable Success")
    @Test
    fun usergroupsEnableSuccess() {
        val response = SuccessfulUsergroupsEnableResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "usergroups.enable", response)
        val verifier = Verifier(response)

        DefaultUsergroupsEnableMethod("", mockTemplate)
                .with(SlackUsergroupsEnableRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }

    @DisplayName("usergroups.enable Failure")
    @Test
    fun usergroupsEnableFailure() {
        val response = ErrorUsergroupsEnableResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "usergroups.enable", response)
        val verifier = Verifier(response)

        DefaultUsergroupsEnableMethod("", mockTemplate)
                .with(SlackUsergroupsEnableRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }
}