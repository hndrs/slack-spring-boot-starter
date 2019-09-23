package com.kreait.slack.api.spring.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsKickResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationKickResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationKickResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsKickMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

@Suppress("UNCHECKED_CAST")
class SpringConversationsKickMethod (private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ConversationsKickMethod() {

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
