package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersLookupByEmailResponse
import com.kreait.slack.api.contract.jackson.group.users.SlackUsersLookupByEmailRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersLookupByEmailResponse
import com.kreait.slack.api.spring.group.users.DefaultUsersLookupByEmailMethod
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.lookupByEmail", response)
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
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.lookupByEmail", response)
        val verifier = Verifier(response)

        DefaultUsersLookupByEmailMethod("", mockTemplate)
                .with(SlackUsersLookupByEmailRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }

}
