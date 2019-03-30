package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.spring.DefaultSlackClient
import io.olaph.slack.client.spring.IntegrationTests
import io.olaph.slack.client.spring.TestConfig
import io.olaph.slack.dto.jackson.group.conversations.ConversationCreateRequest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ConversationCreateFailureTest {

    @Test
    @DisplayName("Create Conversation Failure")
    fun createConversation() {

        val invalidConversationName = ""

        val response = DefaultSlackClient().conversation().create(TestConfig.token())
                .with(ConversationCreateRequest(name = invalidConversationName))
                .onFailure { IntegrationTests.LOG.info("{}", it) }
                .onSuccess { IntegrationTests.LOG.info("{}", it) }
                .invoke()
        Assertions.assertNotNull(response.failure)
        Assertions.assertNotNull(response.failure?.error == "invalid_name")
    }
}
