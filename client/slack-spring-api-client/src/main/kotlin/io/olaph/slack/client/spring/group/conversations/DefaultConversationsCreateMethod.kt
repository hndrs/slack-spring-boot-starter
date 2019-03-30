package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsCreateMethod
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationCreateResponse
import io.olaph.slack.dto.jackson.group.conversations.SlackConversationCreateResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationCreateResponse

@Suppress("UNCHECKED_CAST")
class DefaultConversationsCreateMethod(private val authToken: String) : ConversationsCreateMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationCreateResponse, ErrorConversationCreateResponse> {
        val response = SlackRequestBuilder<SlackConversationCreateResponse>(authToken)
                .with(this.params)
                .toMethod("conversations.create")
                .returnAsType(SlackConversationCreateResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationCreateResponse -> {
                val responseEntity = response.body as SuccessfulConversationCreateResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationCreateResponse -> {
                val responseEntity = response.body as ErrorConversationCreateResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}


