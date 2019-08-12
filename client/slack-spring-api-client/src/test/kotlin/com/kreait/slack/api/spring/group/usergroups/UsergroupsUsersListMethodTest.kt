package com.kreait.slack.api.spring.group.usergroups

import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.usergroups.users.DefaultUsergroupsUsersListMethod
import com.kreait.slack.api.contract.jackson.group.usergroups.users.ErrorUsergroupsUsersListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.SlackUsergroupsUsersListRequest
import com.kreait.slack.api.contract.jackson.group.usergroups.users.SuccessfulUsergroupsUsersListResponse
import com.kreait.slack.api.contract.jackson.group.usergroups.users.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

@DisplayName("Mock usergroups.users.list Method")
class UsergroupsUsersListMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @DisplayName("usergroups.users.list Mock successful")
    @Test
    fun usergroupsUsersListSuccess() {
        val response = SuccessfulUsergroupsUsersListResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "usergroups.users.list", response)
        val verifier = Verifier(response)

        DefaultUsergroupsUsersListMethod("", mockTemplate)
                .with(SlackUsergroupsUsersListRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }

    @DisplayName("usergroups.users.list Mock failed")
    @Test
    fun usergroupsUsersListFailure() {
        val response = ErrorUsergroupsUsersListResponse.sample()
        val verifier = Verifier(response)
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "usergroups.users.list", response)

        DefaultUsergroupsUsersListMethod("", mockTemplate)
                .with(SlackUsergroupsUsersListRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }
}
