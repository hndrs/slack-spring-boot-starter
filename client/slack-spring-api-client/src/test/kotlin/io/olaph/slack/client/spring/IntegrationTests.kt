package io.olaph.slack.client.spring

import io.olaph.slack.dto.jackson.group.conversations.ConversationCreateRequest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class IntegrationTests {


    private val client = DefaultSlackClient()

    companion object {
        val LOG = LoggerFactory.getLogger(IntegrationTests::class.java)
    }

    @Test
    @DisplayName("Integration Suite")
    fun integrationSuite() {
        val channelId = createConversation()

    }

    /**
     * returns a channel id
     */
    fun createConversation(): String {
        val response = client.conversation().create(TestConfig.token())
                .with(ConversationCreateRequest(name = "test-${TestConfig.buildNumber()}"))
                .onFailure { LOG.info("{}", it) }
                .onSuccess { LOG.info("{}", it) }
                .invoke()
        Assertions.assertNotNull(response.success)
        return response.success!!.channel.id
    }
}
