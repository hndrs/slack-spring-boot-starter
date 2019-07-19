package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.conversations.ConversationsSetTopicRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationSetTopicResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationSetTopicResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class ConversationSetTopicTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @DisplayName("Set-Topic Success")
    @Test
    fun conversationSetPurposeSuccess() {
        val response = SuccessfulConversationSetTopicResponse.sample()

        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.setTopic", response)
        val verifier = Verifier(response)

        DefaultConversationsSetTopicMethod("", mockTemplate)
                .with(ConversationsSetTopicRequest.sample())
                .onSuccess { verifier.set(it) }
                .onFailure { }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }

    @DisplayName("Set-Topic Failure")
    @Test
    fun conversationSetTopicFailure() {
        val response = ErrorConversationSetTopicResponse.sample()

        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, "conversations.setTopic", response)
        val verifier = Verifier(response)

        DefaultConversationsSetTopicMethod("", mockTemplate)
                .with(ConversationsSetTopicRequest.sample())
                .onFailure { verifier.set(it) }
                .onSuccess { }
                .invoke()

        mockServer.verify()
        verifier.verify()
    }
}
