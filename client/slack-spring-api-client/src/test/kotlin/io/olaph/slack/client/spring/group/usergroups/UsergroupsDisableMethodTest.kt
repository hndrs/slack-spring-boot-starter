package io.olaph.slack.client.spring.group.usergroups

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsDisableResponse
import io.olaph.slack.dto.jackson.group.usergroups.SlackUsergroupsDisableRequest
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsDisableResponse
import io.olaph.slack.dto.jackson.group.usergroups.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class UsergroupsDisableMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {

        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @DisplayName("usergroups.disable Success")
    @Test
    fun usergroupsDisableSuccess() {

        val response = SuccessfulUsergroupsDisableResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "usergroups.disable")
        val verifier = Verifier(response)

        DefaultUsergroupsDisableMethod("", mockTemplate)
                .with(SlackUsergroupsDisableRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }

    @DisplayName("usergroups.disable Failure")
    @Test
    fun usergroupsDisableFailure() {

        val response = ErrorUsergroupsDisableResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "usergroups.disable")
        val verifier = Verifier(response)

        DefaultUsergroupsDisableMethod("", mockTemplate)
                .with(SlackUsergroupsDisableRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }
}