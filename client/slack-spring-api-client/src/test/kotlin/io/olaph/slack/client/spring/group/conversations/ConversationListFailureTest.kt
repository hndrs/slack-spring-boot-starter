package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.spring.DefaultSlackClient
import io.olaph.slack.client.spring.TEST_LOG
import io.olaph.slack.client.spring.TestConfig
import io.olaph.slack.dto.jackson.group.conversations.ConversationsListRequest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ConversationListFailureTest {

    @Test
    @DisplayName("List Conversation Failure")
    fun closeConversation() {

        val response = DefaultSlackClient().conversation().list(TestConfig.token())
                .with(ConversationsListRequest(cursor = "invalid_cursor"))
                .onFailure { TEST_LOG.info("{}", it) }
                .onSuccess { TEST_LOG.info("{}", it) }
                .invoke()
        Assertions.assertNotNull(response.failure)
        Assertions.assertTrue(response.failure?.error == "invalid_cursor")
    }
}
