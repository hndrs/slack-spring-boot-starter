package com.kreait.slack.api.spring.group.usergroups

import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUsergroupsUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SlackUsergroupsUpdateRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUsergroupsUpdateResponse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class UsergroupsUpdateMethodTest {
    lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @DisplayName("usergroups.update success")
    @Test
    fun usergroupsUpdateSuccess() {
        val response = SuccessfulUsergroupsUpdateResponse.sample()
        val mockServerHelper = MockServerHelper.buildMockRestServer(mockTemplate, "usergroups.update", response)
        val verifier = Verifier(response)

        DefaultUsergroupsUpdateMethod("", mockTemplate)
                .with(SlackUsergroupsUpdateRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()

        mockServerHelper.verify()
        verifier.verify()
    }

    @DisplayName("usergroups.update failure")
    @Test
    fun usergroupsUpdateFailure() {
        val response = ErrorUsergroupsUpdateResponse.sample()
        val mockServerHelper = MockServerHelper.buildMockRestServer(mockTemplate, "usergroups.update", response)
        val verifier = Verifier(response)

        DefaultUsergroupsUpdateMethod("", mockTemplate)
                .with(SlackUsergroupsUpdateRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()

        mockServerHelper.verify()
        verifier.verify()
    }
}
