package com.kreait.slack.api.spring.group.respond

import com.kreait.slack.api.contract.jackson.group.respond.RespondMessageRequest
import com.kreait.slack.api.contract.jackson.group.respond.sample
import com.kreait.slack.api.spring.MockServerHelper
import com.kreait.slack.api.spring.Verifier
import com.kreait.slack.api.spring.group.RestTemplateFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestTemplate

internal class SpringRespondMessageMethodTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackResponseTemplate()
    }

    @Test
    @DisplayName("chat.RespondEphemeral Failure")
    fun chatRespondEphemeralFailure() {
        val response = Unit
        val mockServer = MockServerHelper.buildMockRestServerForUrl(mockTemplate, response, "api.slack.com/test", HttpStatus.BAD_REQUEST)
        val verifier = Verifier(response)

        SpringRespondMessageMethod("api.slack.com/test", mockTemplate)
                .with(RespondMessageRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("chat.RespondEphemeral Success")
    fun chatRespondEphemeralSuccess() {
        val response = Unit
        val mockServer = MockServerHelper.buildMockRestServerForUrl(mockTemplate, response, "api.slack.com/test", HttpStatus.OK)
        val verifier = Verifier(response)

        SpringRespondMessageMethod("api.slack.com/test", mockTemplate)
                .with(RespondMessageRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
