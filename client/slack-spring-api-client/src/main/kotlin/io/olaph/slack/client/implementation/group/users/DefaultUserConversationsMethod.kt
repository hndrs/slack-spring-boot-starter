package io.olaph.slack.client.implementation.group.users

import io.olaph.slack.client.UnknownResponseException
import io.olaph.slack.client.implementation.group.SlackRequestBuilder
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.users.UserConversationsMethod
import io.olaph.slack.dto.jackson.group.users.ErrorUserConversationsResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUserConversationsResponse
import io.olaph.slack.dto.jackson.group.users.UserConversationsResponse

@Suppress("UNCHECKED_CAST")
class DefaultUserConversationsMethod(private val authToken: String) : UserConversationsMethod() {

    override fun request(): ApiCallResult<SuccessfulUserConversationsResponse, ErrorUserConversationsResponse> {
        val response = SlackRequestBuilder<UserConversationsResponse>(authToken)
                .toMethod("users.conversations")
                .returnAsType(UserConversationsResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when {
            response.body is SuccessfulUserConversationsResponse -> {
                val responseEntity = response.body as SuccessfulUserConversationsResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            response.body is ErrorUserConversationsResponse -> {
                val responseEntity = response.body as ErrorUserConversationsResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
            else -> {
                throw UnknownResponseException(this::class, response)
            }
        }
    }
}
