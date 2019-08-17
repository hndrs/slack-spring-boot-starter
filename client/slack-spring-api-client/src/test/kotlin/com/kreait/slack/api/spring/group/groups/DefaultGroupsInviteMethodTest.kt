package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsInviteResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsInviteRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsInviteResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class GroupsInviteTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("groups.invite Failure")
    fun groupsInviteFailure() {
        val response = ErrorGroupsInviteResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.invite", response)
        val verifier = Verifier(response)

        DefaultGroupsInviteMethod("", mockTemplate)
                .with(GroupsInviteRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("groups.invite Success")
    fun groupsInviteSuccess() {
        val response = SuccessfulGroupsInviteResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.invite", response)
        val verifier = Verifier(response)

        DefaultGroupsInviteMethod("", mockTemplate)
                .with(GroupsInviteRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
