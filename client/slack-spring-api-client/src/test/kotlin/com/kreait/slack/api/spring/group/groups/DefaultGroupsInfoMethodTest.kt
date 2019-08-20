package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsInfoResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsInfoRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsInfoResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class GroupsInfoTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("groups.info Failure")
    fun GroupsInfoFailure() {
        val response = ErrorGroupsInfoResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.info", response)
        val verifier = Verifier(response)

        DefaultGroupsInfoMethod("", mockTemplate)
                .with(GroupsInfoRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("groups.info Success")
    fun GroupsInfoSuccess() {
        val response = SuccessfulGroupsInfoResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.info", response)
        val verifier = Verifier(response)

        DefaultGroupsInfoMethod("", mockTemplate)
                .with(GroupsInfoRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}