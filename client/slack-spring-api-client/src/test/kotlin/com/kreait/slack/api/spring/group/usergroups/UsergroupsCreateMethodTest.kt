package com.kreait.slack.api.spring.group.usergroups

import com.kreait.slack.api.contract.jackson.group.usergroups.ErrorUsergroupsCreateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.SlackUsergroupsCreateRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.SuccessfulUsergroupsCreateResponse
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class UsergroupsCreateMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {

        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @DisplayName("usergroups.create Success")
    @Test
    fun usergroupsCreateSuccess() {

        val response = SuccessfulUsergroupsCreateResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "usergroups.create", response)
        val verifier = Verifier(response)

        DefaultUsergroupsCreateMethod("", mockTemplate)
                .with(SlackUsergroupsCreateRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }

    @DisplayName("usergroups.create Failure")
    @Test
    fun usergroupsCreateFailure() {

        val response = ErrorUsergroupsCreateResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "usergroups.create", response)
        val verifier = Verifier(response)

        DefaultUsergroupsCreateMethod("", mockTemplate)
                .with(SlackUsergroupsCreateRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }
}
