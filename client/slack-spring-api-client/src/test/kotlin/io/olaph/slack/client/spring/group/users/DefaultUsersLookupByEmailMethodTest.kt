package io.olaph.slack.client.spring.group.users

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.users.ErrorUsersLookupByEmailResponse
import io.olaph.slack.dto.jackson.group.users.SlackUsersLookupByEmailRequest
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersLookupByEmailResponse
import io.olaph.slack.dto.jackson.group.users.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class DefaultUsersLookupByEmailMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @DisplayName("users.lookupByEmail Success")
    @Test
    fun lookupByEmailSuccess() {

        val response = SuccessfulUsersLookupByEmailResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "users.lookupByEmail?email=user@acme.com")
        val verifier = Verifier(response)

        DefaultUsersLookupByEmailMethod("", mockTemplate)
                .with(SlackUsersLookupByEmailRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }


    @DisplayName("users.lookupByEmail Failure")
    @Test
    fun lookupByEmailFailure() {

        val response = ErrorUsersLookupByEmailResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "users.lookupByEmail?email=user@acme.com")
        val verifier = Verifier(response)

        DefaultUsersLookupByEmailMethod("", mockTemplate)
                .with(SlackUsersLookupByEmailRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }

}