package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorLookupByEmailResponse
import com.kreait.slack.api.contract.jackson.group.users.LookupByEmailRequest
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulLookupByEmailResponse
import com.kreait.slack.api.contract.jackson.group.users.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
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
        val response = SuccessfulLookupByEmailResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.lookupByEmail", response)
        val verifier = Verifier(response)

        DefaultUsersLookupByEmailMethod("", mockTemplate)
                .with(LookupByEmailRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }


    @DisplayName("users.lookupByEmail Failure")
    @Test
    fun lookupByEmailFailure() {
        val response = ErrorLookupByEmailResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "users.lookupByEmail", response)
        val verifier = Verifier(response)

        DefaultUsersLookupByEmailMethod("", mockTemplate)
                .with(LookupByEmailRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }

}
