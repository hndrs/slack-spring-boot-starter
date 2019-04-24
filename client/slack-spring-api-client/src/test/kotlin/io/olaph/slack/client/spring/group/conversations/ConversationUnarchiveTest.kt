package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.conversations.ConversationUnarchiveRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationUnarchiveResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationUnarchiveResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class ConversationUnarchiveTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("conversations.unarchive Failure")
    fun conversationUnarchiveFailure() {
        val response = ErrorConversationUnarchiveResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "conversations.unarchive")
        val verifier = Verifier(response)

        DefaultConversationsUnarchiveMethod("", mockTemplate)
                .with(ConversationUnarchiveRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("conversations.unarchive Success")
    fun conversationUnarchiveSuccess() {
        val response = SuccessfulConversationUnarchiveResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "conversations.unarchive")
        val verifier = Verifier(response)

        DefaultConversationsUnarchiveMethod("", mockTemplate)
                .with(ConversationUnarchiveRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
