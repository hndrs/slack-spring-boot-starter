package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.conversations.ConversationArchiveRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationArchiveResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationArchiveResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class ConversationArchiveTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("conversations.archive Failure")
    fun conversationCloseFailure() {
        val response = ErrorConversationArchiveResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.archive", response)
        val verifier = Verifier(response)

        DefaultConversationsArchiveMethod("", mockTemplate)
                .with(ConversationArchiveRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("conversations.archive Success")
    fun conversationCloseSuccess() {
        val response = SuccessfulConversationArchiveResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.archive", response)
        val verifier = Verifier(response)

        DefaultConversationsArchiveMethod("", mockTemplate)
                .with(ConversationArchiveRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
