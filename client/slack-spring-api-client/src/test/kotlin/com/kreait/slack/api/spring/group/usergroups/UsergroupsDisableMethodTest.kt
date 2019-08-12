package com.kreait.slack.api.spring.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorDisableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.DisableRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulDisableResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
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
        val response = SuccessfulDisableResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "usergroups.disable", response)
        val verifier = Verifier(response)

        DefaultUsergroupsDisableMethod("", mockTemplate)
                .with(DisableRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }

    @DisplayName("usergroups.disable Failure")
    @Test
    fun usergroupsDisableFailure() {
        val response = ErrorDisableResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "usergroups.disable", response)
        val verifier = Verifier(response)

        DefaultUsergroupsDisableMethod("", mockTemplate)
                .with(DisableRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }
}
