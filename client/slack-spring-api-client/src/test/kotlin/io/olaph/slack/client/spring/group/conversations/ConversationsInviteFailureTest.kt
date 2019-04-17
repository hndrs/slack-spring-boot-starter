package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.dto.jackson.group.conversations.ConversationsInviteRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationInviteResponse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class ConversationsInviteFailureTest() {

    @Test
    @DisplayName("conversations.invite Failure")
    fun closeConversation() {

        val mockTemplate = RestTemplate()
        val response = ErrorConversationInviteResponse(false, "")
        val value = MockServerHelper.getObjectString(response)
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, value, "conversations.invite")

        DefaultConversationsInviteMethod("", mockTemplate)
                .with(ConversationsInviteRequest("channel_id", listOf("user_id")))
                .onFailure { Assertions.assertEquals(response, it) }
                .onSuccess { }
                .invoke()

        mockServer.verify()
    }
}
