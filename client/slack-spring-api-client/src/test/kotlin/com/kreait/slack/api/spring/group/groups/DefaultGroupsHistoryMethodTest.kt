package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsHistoryResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsHistoryRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsHistoryResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class GroupsHistoryTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("groups.history Failure")
    fun GroupsCloseFailure() {
        val response = ErrorGroupsHistoryResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.history", response)
        val verifier = Verifier(response)

        DefaultGroupsHistoryMethod("", mockTemplate)
                .with(GroupsHistoryRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("groups.history Success")
    fun GroupsCloseSuccess() {
        val response = SuccessfulGroupsHistoryResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.history", response)
        val verifier = Verifier(response)

        DefaultGroupsHistoryMethod("", mockTemplate)
                .with(GroupsHistoryRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}