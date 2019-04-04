package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.UnknownResponseException
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.client.group.conversations.ConversationsOpenMethod
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationOpenResponse
import io.olaph.slack.dto.jackson.group.conversations.ConversationOpenResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationOpenResponse

@Suppress("UNCHECKED_CAST")
class DefaultConversationsOpenMethod(private val authToken: String) : ConversationsOpenMethod(){

    override fun request(): ApiCallResult<SuccessfulConversationOpenResponse, ErrorConversationOpenResponse> {
        val response = SlackRequestBuilder<ConversationOpenResponse>(authToken)
                .with(this.params)
                .toMethod("conversations.open")
                .returnAsType(ConversationOpenResponse::class.java)
                .postWithJsonBody()

        return when {
            response.body is SuccessfulConversationOpenResponse -> {
                val responseEntity = response.body as SuccessfulConversationOpenResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            response.body is ErrorConversationOpenResponse -> {
                val responseEntity = response.body as ErrorConversationOpenResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
            else -> {
                throw UnknownResponseException(this::class, response)
            }
        }
    }
}
