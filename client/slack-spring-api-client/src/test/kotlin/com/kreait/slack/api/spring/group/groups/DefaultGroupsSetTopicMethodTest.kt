package com.kreait.slack.api.spring.group.groups

import com.kreait.slack.api.contract.jackson.group.groups.ErrorGroupsSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.groups.GroupsSetTopicRequest
import com.kreait.slack.api.contract.jackson.group.groups.SuccessfulGroupsSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.groups.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class GroupsSetTopicTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("groups.setTopic Failure")
    fun GroupsCloseFailure() {
        val response = ErrorGroupsSetTopicResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.setTopic", response)
        val verifier = Verifier(response)

        DefaultGroupsSetTopicMethod("", mockTemplate)
                .with(GroupsSetTopicRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("groups.setTopic Success")
    fun GroupsCloseSuccess() {
        val response = SuccessfulGroupsSetTopicResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "groups.setTopic", response)
        val verifier = Verifier(response)

        DefaultGroupsSetTopicMethod("", mockTemplate)
                .with(GroupsSetTopicRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}