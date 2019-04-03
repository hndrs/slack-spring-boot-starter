package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsCloseMethod
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationCloseResponse
import io.olaph.slack.dto.jackson.group.conversations.SlackConversationCloseResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationCloseResponse

@Suppress("UNCHECKED_CAST")
class DefaultConversationsCloseMethod constructor(private val authToken: String) : ConversationsCloseMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationCloseResponse, ErrorConversationCloseResponse> {
        val response = SlackRequestBuilder<SlackConversationCloseResponse>(authToken)
                .with(this.params)
                .toMethod("conversations.close")
                .returnAsType(SlackConversationCloseResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationCloseResponse -> {
                val responseEntity = response.body as SuccessfulConversationCloseResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationCloseResponse -> {
                val responseEntity = response.body as ErrorConversationCloseResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}