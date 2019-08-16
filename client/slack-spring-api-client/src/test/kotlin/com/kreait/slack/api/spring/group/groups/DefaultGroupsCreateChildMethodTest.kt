package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsCreateChildResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsCreateChildRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsCreateChildResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class GroupsCreateChildTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("groups.createChild Failure")
    fun GroupsCloseFailure() {
        val response = ErrorGroupsCreateChildResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.createChild", response)
        val verifier = Verifier(response)

        DefaultGroupsCreateChildMethod("", mockTemplate)
                .with(GroupsCreateChildRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("groups.createChild Success")
    fun GroupsCloseSuccess() {
        val response = SuccessfulGroupsCreateChildResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.createChild", response)
        val verifier = Verifier(response)

        DefaultGroupsCreateChildMethod("", mockTemplate)
                .with(GroupsCreateChildRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}