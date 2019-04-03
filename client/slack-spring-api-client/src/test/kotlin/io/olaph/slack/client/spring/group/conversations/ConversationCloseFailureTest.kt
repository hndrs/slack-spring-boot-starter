package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.spring.DefaultSlackClient
import io.olaph.slack.client.spring.TEST_LOG
import io.olaph.slack.client.spring.TestConfig
import io.olaph.slack.dto.jackson.group.conversations.ConversationCloseRequest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ConversationCloseFailureTest {

    @Test
    @DisplayName("Close Conversation Failure")
    fun closeConversation() {

        val invalidChannelId = "invalidChannelId"
        val response = DefaultSlackClient().conversation().close(TestConfig.token())
                .with(ConversationCloseRequest(channel = invalidChannelId))
                .onFailure { TEST_LOG.info("{}", it) }
                .onSuccess { TEST_LOG.info("{}", it) }
                .invoke()
        Assertions.assertNotNull(response.failure)
        Assertions.assertTrue(response.failure?.error == "channel_not_found")
    }
}
