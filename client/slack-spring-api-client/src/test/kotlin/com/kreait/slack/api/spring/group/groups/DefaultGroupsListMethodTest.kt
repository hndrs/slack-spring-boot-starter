package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsListResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsListRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsListResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class GroupsListTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("groups.list Failure")
    fun GroupsCloseFailure() {
        val response = ErrorGroupsListResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.list", response)
        val verifier = Verifier(response)

        DefaultGroupsListMethod("", mockTemplate)
                .with(GroupsListRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("groups.list Success")
    fun GroupsCloseSuccess() {
        val response = SuccessfulGroupsListResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.list", response)
        val verifier = Verifier(response)

        DefaultGroupsListMethod("", mockTemplate)
                .with(GroupsListRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}