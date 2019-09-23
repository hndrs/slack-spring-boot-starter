package com.kreait.slack.api.spring.group.conversations


import com.kreait.slack.api.contract.jackson.group.conversations.ConversationRepliesResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationRepliesResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationRepliesResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsRepliesMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class SpringConversationsRepliesMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ConversationsRepliesMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationRepliesResponse, ErrorConversationRepliesResponse> {
        val response = SlackRequestBuilder<ConversationRepliesResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("conversations.replies")
                .returnAsType(ConversationRepliesResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulConversationRepliesResponse -> {
                val responseEntity = response.body as SuccessfulConversationRepliesResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationRepliesResponse -> {
                val responseEntity = response.body as ErrorConversationRepliesResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}


