package io.olaph.slack.client.spring.group.usergroups

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.usergroups.users.DefaultUsergroupsUsersUpdateMethod
import io.olaph.slack.dto.jackson.group.usergroups.users.ErrorUsergroupUsersUpdateResponse
import io.olaph.slack.dto.jackson.group.usergroups.users.SlackUsergroupUsersUpdateRequest
import io.olaph.slack.dto.jackson.group.usergroups.users.SuccessfulUsergroupUsersUpdateResponse
import io.olaph.slack.dto.jackson.group.usergroups.users.sample
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "usergroups.users.update")
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "usergroups.users.update")
        val verifier = Verifier(response)

        DefaultUsergroupsUsersUpdateMethod("", mockTemplate)
                .with(SlackUsergroupUsersUpdateRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }
}