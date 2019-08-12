package com.kreait.slack.api.spring.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUsergroupsEnableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SlackUsergroupsEnableRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUsergroupsEnableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
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
