package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsMarkResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsMarkRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsMarkResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class GroupsMarkTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("groups.mark Failure")
    fun GroupsCloseFailure() {
        val response = ErrorGroupsMarkResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.mark", response)
        val verifier = Verifier(response)

        DefaultGroupsMarkMethod("", mockTemplate)
                .with(GroupsMarkRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("groups.mark Success")
    fun GroupsCloseSuccess() {
        val response = SuccessfulGroupsMarkResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.mark", response)
        val verifier = Verifier(response)

        DefaultGroupsMarkMethod("", mockTemplate)
                .with(GroupsMarkRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}