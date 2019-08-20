package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsKickResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsKickRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsKickResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class GroupsKickTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("groups.kick Failure")
    fun GroupsCloseFailure() {
        val response = ErrorGroupsKickResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.kick", response)
        val verifier = Verifier(response)

        DefaultGroupsKickMethod("", mockTemplate)
                .with(GroupsKickRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("groups.kick Success")
    fun GroupsCloseSuccess() {
        val response = SuccessfulGroupsKickResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.kick", response)
        val verifier = Verifier(response)

        DefaultGroupsKickMethod("", mockTemplate)
                .with(GroupsKickRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}