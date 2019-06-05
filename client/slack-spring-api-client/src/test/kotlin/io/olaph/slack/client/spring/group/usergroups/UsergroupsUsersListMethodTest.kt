package io.olaph.slack.client.spring.group.usergroups

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.usergroups.users.DefaultUsergroupsUsersListMethod
import io.olaph.slack.dto.jackson.group.usergroups.users.ErrorUsergroupsUsersListResponse
import io.olaph.slack.dto.jackson.group.usergroups.users.SlackUsergroupsUsersListRequest
import io.olaph.slack.dto.jackson.group.usergroups.users.SuccessfulUsergroupsUsersListResponse
import io.olaph.slack.dto.jackson.group.usergroups.users.sample
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "usergroups.users.list?usergroup=&include_disabled=true")
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "usergroups.users.list?usergroup=&include_disabled=true")
        val verifier = Verifier(response)

        DefaultUsergroupsUsersListMethod("", mockTemplate)
                .with(SlackUsergroupsUsersListRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }
}