package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsRenameResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsRenameRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsRenameResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class GroupsRenameTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("groups.rename Failure")
    fun GroupsCloseFailure() {
        val response = ErrorGroupsRenameResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.rename", response)
        val verifier = Verifier(response)

        DefaultGroupsRenameMethod("", mockTemplate)
                .with(GroupsRenameRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("groups.rename Success")
    fun GroupsCloseSuccess() {
        val response = SuccessfulGroupsRenameResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.rename", response)
        val verifier = Verifier(response)

        DefaultGroupsRenameMethod("", mockTemplate)
                .with(GroupsRenameRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}