package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.conversations.ConversationsRenameRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationsRenameResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationsRenameResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class ConversationRenameTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("conversations.rename Failure")
    fun conversationRenameFailure() {
        val response = ErrorConversationsRenameResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.rename", response)
        val verifier = Verifier(response)

        DefaultConversationsRenameMethod("", mockTemplate)
                .with(ConversationsRenameRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("conversations.Rename Success")
    fun conversationRenameSuccess() {
        val response = SuccessfulConversationsRenameResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.rename", response)
        val verifier = Verifier(response)

        DefaultConversationsRenameMethod("", mockTemplate)
                .with(ConversationsRenameRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
