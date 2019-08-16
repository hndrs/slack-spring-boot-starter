package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsArchiveResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsArchiveRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsArchiveResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class GroupsArchiveTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("groups.archive Failure")
    fun GroupsCloseFailure() {
        val response = ErrorGroupsArchiveResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.archive", response)
        val verifier = Verifier(response)

        DefaultGroupsArchiveMethod("", mockTemplate)
                .with(GroupsArchiveRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("groups.archive Success")
    fun GroupsCloseSuccess() {
        val response = SuccessfulGroupsArchiveResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.archive", response)
        val verifier = Verifier(response)

        DefaultGroupsArchiveMethod("", mockTemplate)
                .with(GroupsArchiveRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}