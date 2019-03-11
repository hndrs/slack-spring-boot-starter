package io.olaph.slack.client.implementation.group.conversations

import io.olaph.slack.client.UnknownResponseException
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.implementation.group.SlackRequestBuilder
import io.olaph.slack.client.group.conversations.ConversationsOpenMethod
import io.olaph.slack.dto.jackson.group.conversations.ErrorOpenResponse
import io.olaph.slack.dto.jackson.group.conversations.SlackOpenResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulOpenResponse

@Suppress("UNCHECKED_CAST")
class DefaultConversationsOpenMethod(private val authToken: String) : ConversationsOpenMethod(){

    override fun request(): ApiCallResult<SuccessfulOpenResponse, ErrorOpenResponse> {
        val response = SlackRequestBuilder<SlackOpenResponse>(authToken)
                .with(this.params)
                .toMethod("conversations.open")
                .returnAsType(SlackOpenResponse::class.java)
                .postWithJsonBody()

        return when {
            response.body is SuccessfulOpenResponse -> {
                val responseEntity = response.body as SuccessfulOpenResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            response.body is ErrorOpenResponse -> {
                val responseEntity = response.body as ErrorOpenResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
            else -> {
                throw UnknownResponseException(this::class, response)
            }
        }
    }
}
