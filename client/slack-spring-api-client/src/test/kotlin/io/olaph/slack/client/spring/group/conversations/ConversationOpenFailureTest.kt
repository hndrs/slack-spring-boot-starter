package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.spring.DefaultSlackClient
import io.olaph.slack.client.spring.TEST_LOG
import io.olaph.slack.client.spring.TestConfig
import io.olaph.slack.dto.jackson.group.conversations.ConversationsOpenRequest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ConversationOpenFailureTest {

    @Test
    @DisplayName("Open Conversation Failure")
    fun closeConversation() {

        val invalidChannelId = "invalidChannelId"
        val response = DefaultSlackClient().conversation().open(TestConfig.token())
                .with(ConversationsOpenRequest())
                .onFailure { TEST_LOG.info("{}", it) }
                .onSuccess { TEST_LOG.info("{}", it) }
                .invoke()
        Assertions.assertNotNull(response.failure)
        Assertions.assertTrue(response.failure?.error == "channel_not_found")
    }
}
