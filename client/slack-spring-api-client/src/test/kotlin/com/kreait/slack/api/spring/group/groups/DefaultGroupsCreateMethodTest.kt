package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsCreateResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsCreateRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsCreateResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class GroupsCreateTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("groups.create Failure")
    fun GroupsCloseFailure() {
        val response = ErrorGroupsCreateResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.create", response)
        val verifier = Verifier(response)

        DefaultGroupsCreateMethod("", mockTemplate)
                .with(GroupsCreateRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("groups.create Success")
    fun GroupsCloseSuccess() {
        val response = SuccessfulGroupsCreateResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.create", response)
        val verifier = Verifier(response)

        DefaultGroupsCreateMethod("", mockTemplate)
                .with(GroupsCreateRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}