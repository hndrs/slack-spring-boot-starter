package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsUnarchiveRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultGroupsUnarchiveMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("groups.unarchive Failure")
    fun groupsUnarchiveFailure() {
        val response = ErrorGroupsUnarchiveResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.unarchive", response)
        val verifier = Verifier(response)

        DefaultGroupsUnarchiveMethod("", mockTemplate)
                .with(GroupsUnarchiveRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("groups.unarchive Success")
    fun groupsUnarchiveSuccess() {
        val response = SuccessfulGroupsUnarchiveResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.unarchive", response)
        val verifier = Verifier(response)

        DefaultGroupsUnarchiveMethod("", mockTemplate)
                .with(GroupsUnarchiveRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
