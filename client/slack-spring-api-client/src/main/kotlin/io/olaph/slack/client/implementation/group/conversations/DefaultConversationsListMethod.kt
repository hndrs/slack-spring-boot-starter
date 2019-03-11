package io.olaph.slack.client.implementation.group.conversations

import io.olaph.slack.client.UnknownResponseException
import io.olaph.slack.client.implementation.group.SlackRequestBuilder
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsListMethod
import io.olaph.slack.dto.jackson.group.conversations.ErrorGetConversationListResponse
import io.olaph.slack.dto.jackson.group.conversations.SlackGetConversationListResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccesfulGetConversationListResponse

@Suppress("UNCHECKED_CAST")
class DefaultConversationsListMethod(private val authToken: String) : ConversationsListMethod() {

    override fun request(): ApiCallResult<SuccesfulGetConversationListResponse, ErrorGetConversationListResponse> {
        val response = SlackRequestBuilder<SlackGetConversationListResponse>(authToken)
                .with(this.params)
                .toMethod("conversations.list")
                .returnAsType(SlackGetConversationListResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when {
            response.body is SuccesfulGetConversationListResponse -> {
                val responseEntity = response.body as SuccesfulGetConversationListResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            response.body is ErrorGetConversationListResponse -> {
                val responseEntity = response.body as ErrorGetConversationListResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
            else -> {
                throw UnknownResponseException(this::class, response)
            }
        }
    }
}


