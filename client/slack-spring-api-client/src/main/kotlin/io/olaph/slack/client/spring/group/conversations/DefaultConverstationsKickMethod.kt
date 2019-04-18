package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsKickMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.conversations.ConversationsKickResponse
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationKickResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationKickResponse
import org.springframework.web.client.RestTemplate

@Suppress("UNCHECKED_CAST")
class DefaultConverstationsKickMethod (private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ConversationsKickMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationKickResponse, ErrorConversationKickResponse> {
        val response = SlackRequestBuilder<ConversationsKickResponse>(authToken, restTemplate)
                .toMethod("conversations.kick")
                .returnAsType(ConversationsKickResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationKickResponse -> {
                val responseEntity = response.body as SuccessfulConversationKickResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorConversationKickResponse -> {
                val responseEntity = response.body as ErrorConversationKickResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}