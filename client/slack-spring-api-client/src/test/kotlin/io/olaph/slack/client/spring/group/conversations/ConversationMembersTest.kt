package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.conversations.ConversationMembersRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationMembersResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationMembersResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class ConversationMembersTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("conversations.members Failure")
    fun conversationMembersFailure() {
        val response = ErrorConversationMembersResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "conversations.members?channel=&cursor=&limit=100")
        val verifier = Verifier(response)

        DefaultConversationsMembersMethod("", mockTemplate)
                .with(ConversationMembersRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("conversations.members Success")
    fun conversationMembersSuccess() {
        val response = SuccessfulConversationMembersResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "conversations.members?channel=&cursor=&limit=100")
        val verifier = Verifier(response)

        DefaultConversationsMembersMethod("", mockTemplate)
                .with(ConversationMembersRequest.sample())
                .onSuccess { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
