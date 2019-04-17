package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.spring.MockServerHelper
import io.olaph.slack.client.spring.Verifier
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.dto.jackson.group.conversations.ConversationsLeaveRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationLeaveResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationLeaveResponse
import io.olaph.slack.dto.jackson.group.conversations.sample
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.web.client.RestTemplate

class ConversationLeaveTest {

    private lateinit var mockTemplate: RestTemplate

    @BeforeEach
    fun setup() {
        mockTemplate = RestTemplateFactory.slackTemplate()
    }

    @Test
    @DisplayName("conversations.leave Failure")
    fun conversationLeaveFailure() {
        val response = ErrorConversationLeaveResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "conversations.leave")
        val verifier = Verifier(response)

        DefaultConversationsLeaveMethod("", mockTemplate)
                .with(ConversationsLeaveRequest.sample())
                .onFailure { verifier.set(it) }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }

    @Test
    @DisplayName("conversations.leave Success")
    fun conversationLeaveSuccess() {
        val response = SuccessfulConversationLeaveResponse.sample()
        val mockServer = MockServerHelper.buildMockRestServer(mockTemplate, response, "conversations.leave")
        val verifier = Verifier(response)

        DefaultConversationsLeaveMethod("", mockTemplate)
                .with(ConversationsLeaveRequest.sample())
                .onSuccess { verifier.set(it) }
                .onFailure { }
                .invoke()
        mockServer.verify()
        verifier.verify()
    }
}
