package io.olaph.slack.client.spring

import io.olaph.slack.dto.jackson.common.types.Conversation
import io.olaph.slack.dto.jackson.group.conversations.ConversationCreateRequest
import io.olaph.slack.dto.jackson.group.conversations.ConversationMembersRequest
import io.olaph.slack.dto.jackson.group.conversations.ConversationsListRequest
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
        val memberIds = membersConversations(channelId)
        val listConversations = listConversations()
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

    /**
     * returns memberIds
     */
    fun membersConversations(channelId: String): List<String> {
        val response = client.conversation().members(TestConfig.token())
                .with(ConversationMembersRequest(channelId = channelId))
                .onFailure { LOG.info("{}", it) }
                .onSuccess { LOG.info("{}", it) }
                .invoke()
        Assertions.assertNotNull(response.success)

        return response.success!!.memberIds
    }

    /**
     * returns Conversations
     */
    fun listConversations(): List<Conversation> {
        val response = client.conversation().list(TestConfig.token())
                .with(ConversationsListRequest())
                .onFailure { LOG.info("{}", it) }
                .onSuccess { LOG.info("{}", it) }
                .invoke()
        Assertions.assertNotNull(response.success)

        return response.success!!.channels
    }
}
