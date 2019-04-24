package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.conversations.ConversationsInfoRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationsInfoResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationsInfoResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class ConversationsInfoTest {
    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("conversations.info Failure")
    fun conversationListFailure() {
        val response = ErrorConversationsInfoResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "conversations.info?channel=&include_locale=false&include_num_members=false")
        val verifier = Verifier(response)

        DefaultConversationsInfoMethod("", mockTemplate)
                .with(ConversationsInfoRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("conversations.info Success")
    fun conversationListSuccess() {
        val response = SuccessfulConversationsInfoResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "conversations.info?channel=&include_locale=false&include_num_members=false")
        val verifier = Verifier(response)

        DefaultConversationsInfoMethod("", mockTemplate)
                .with(ConversationsInfoRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
