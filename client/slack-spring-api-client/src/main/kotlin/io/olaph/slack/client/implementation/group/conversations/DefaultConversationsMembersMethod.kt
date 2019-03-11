package io.olaph.slack.client.implementation.group.conversations

import io.olaph.slack.client.UnknownResponseException
import io.olaph.slack.client.implementation.group.SlackRequestBuilder
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsMembersMethod
import io.olaph.slack.dto.jackson.group.conversations.ErrorGetMembersResponse
import io.olaph.slack.dto.jackson.group.conversations.SlackGetMembersResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulGetMembersResponse

/**
 * https://api.slack.com/methods/conversations.members
 */
@Suppress("UNCHECKED_CAST")
class DefaultConversationsMembersMethod(private val authToken: String) : ConversationsMembersMethod() {

    override fun request(): ApiCallResult<SuccessfulGetMembersResponse, ErrorGetMembersResponse> {
        val response = SlackRequestBuilder<SlackGetMembersResponse>(authToken)
                .toMethod("conversations.members")
                .returnAsType(SlackGetMembersResponse::class.java)
                .postUrlEncoded(this.params)

        return when {
            response.body is SuccessfulGetMembersResponse -> {
                val responseEntity = response.body as SuccessfulGetMembersResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            response.body is ErrorGetMembersResponse -> {
                val responseEntity = response.body as ErrorGetMembersResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
            else -> {
                throw UnknownResponseException(this::class, response)
            }
        }
    }
}
