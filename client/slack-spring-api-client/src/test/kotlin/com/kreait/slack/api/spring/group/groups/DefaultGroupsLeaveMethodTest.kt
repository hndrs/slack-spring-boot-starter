package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsLeaveResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsLeaveRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsLeaveResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class GroupsLeaveTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("groups.leave Failure")
    fun GroupsCloseFailure() {
        val response = ErrorGroupsLeaveResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.leave", response)
        val verifier = Verifier(response)

        DefaultGroupsLeaveMethod("", mockTemplate)
                .with(GroupsLeaveRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("groups.leave Success")
    fun GroupsCloseSuccess() {
        val response = SuccessfulGroupsLeaveResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.leave", response)
        val verifier = Verifier(response)

        DefaultGroupsLeaveMethod("", mockTemplate)
                .with(GroupsLeaveRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}