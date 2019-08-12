package com.kreait.slack.api.spring.group.usergroups

import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.usergroups.users.DefaultUsergroupsUsersUpdateMethod
import com.kreait.slack.api.contract.jackson.group.usergroups.users.ErrorUsergroupUsersUpdateResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.SlackUsergroupUsersUpdateRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.users.SuccessfulUsergroupUsersUpdateResponse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class UsergroupsUsersUpdateMethodTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @DisplayName("usergroups.users.update Success")
    @Test
    fun usergroupsUsersUpdateSuccess() {
        val response = SuccessfulUsergroupUsersUpdateResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "usergroups.users.update", response)
        val verifier = Verifier(response)

        DefaultUsergroupsUsersUpdateMethod("", mockTemplate)
                .with(SlackUsergroupUsersUpdateRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }

    @DisplayName("usergroups.users.update Failure")
    @Test
    fun usergroupsUsersUpdateFailure() {
        val response = ErrorUsergroupUsersUpdateResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "usergroups.users.update", response)
        val verifier = Verifier(response)

        DefaultUsergroupsUsersUpdateMethod("", mockTemplate)
                .with(SlackUsergroupUsersUpdateRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }
}
