package com.kreait.slack.api.spring.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.ListRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
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
        val response = SuccessfulListResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "usergroups.list", response)
        val verifier = Verifier(response)

        DefaultUsergroupsListMethod("", mockTemplate)
                .with(ListRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()

        verifier.verify()
        mockServer.verify()
    }

    @DisplayName("usergroups.list Method Failure")
    @Test
    fun usergroupsListMethodFailure() {
        val response = ErrorListResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "usergroups.list", response)
        val verifier = Verifier(response)

        DefaultUsergroupsListMethod("", mockTemplate)
                .with(ListRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()

        verifier.verify()
        mockServer.verify()
    }
}
