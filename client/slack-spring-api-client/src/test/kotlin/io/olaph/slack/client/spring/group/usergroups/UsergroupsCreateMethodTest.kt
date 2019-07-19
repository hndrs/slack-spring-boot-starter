package io.olaph.slack.client.spring.group.usergroups

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.usergroups.ErrorUsergroupsCreateResponse
import io.olaph.slack.dto.jackson.group.usergroups.SlackUsergroupsCreateRequest
import io.olaph.slack.dto.jackson.group.usergroups.SuccessfulUsergroupsCreateResponse
import io.olaph.slack.dto.jackson.group.usergroups.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class UsergroupsCreateMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {

        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @DisplayName("usergroups.create Success")
    @Test
    fun usergroupsCreateSuccess() {

        val response = SuccessfulUsergroupsCreateResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "usergroups.create", response)
        val verifier = Verifier(response)

        DefaultUsergroupsCreateMethod("", mockTemplate)
                .with(SlackUsergroupsCreateRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }

    @DisplayName("usergroups.create Failure")
    @Test
    fun usergroupsCreateFailure() {

        val response = ErrorUsergroupsCreateResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "usergroups.create", response)
        val verifier = Verifier(response)

        DefaultUsergroupsCreateMethod("", mockTemplate)
                .with(SlackUsergroupsCreateRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }
}