package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsRepliesResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsRepliesRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsRepliesResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class GroupsRepliesTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("groups.replies Failure")
    fun GroupsCloseFailure() {
        val response = ErrorGroupsRepliesResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.replies", response)
        val verifier = Verifier(response)

        DefaultGroupsRepliesMethod("", mockTemplate)
                .with(GroupsRepliesRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("groups.replies Success")
    fun GroupsCloseSuccess() {
        val response = SuccessfulGroupsRepliesResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.replies", response)
        val verifier = Verifier(response)

        DefaultGroupsRepliesMethod("", mockTemplate)
                .with(GroupsRepliesRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}