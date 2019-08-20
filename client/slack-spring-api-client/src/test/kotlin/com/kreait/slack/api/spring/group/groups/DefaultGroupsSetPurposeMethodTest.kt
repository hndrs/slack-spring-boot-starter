package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsSetPurposeRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class GroupsSetPurposeTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("groups.setPurpose Failure")
    fun GroupsSetPurposeFailure() {
        val response = ErrorGroupsSetPurposeResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.setPurpose", response)
        val verifier = Verifier(response)

        DefaultGroupsSetPurposeMethod("", mockTemplate)
                .with(GroupsSetPurposeRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("groups.setPurpose Success")
    fun GroupsSetPurposeSuccess() {
        val response = SuccessfulGroupsSetPurposeResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.setPurpose", response)
        val verifier = Verifier(response)

        DefaultGroupsSetPurposeMethod("", mockTemplate)
                .with(GroupsSetPurposeRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}