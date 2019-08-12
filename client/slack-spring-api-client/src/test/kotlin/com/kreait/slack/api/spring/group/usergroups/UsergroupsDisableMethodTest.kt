package com.kreait.slack.api.spring.group.usergroups

import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUsergroupsDisableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SlackUsergroupsDisableRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUsergroupsDisableResponse
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "usergroups.disable", response)
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "usergroups.disable", response)
        val verifier = Verifier(response)

        DefaultUsergroupsDisableMethod("", mockTemplate)
                .with(SlackUsergroupsDisableRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }
}
