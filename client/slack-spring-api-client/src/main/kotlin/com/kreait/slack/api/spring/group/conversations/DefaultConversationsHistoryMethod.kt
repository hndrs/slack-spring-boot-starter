package com.kreait.slack.api.spring.group.conversations


import com.kreait.slack.api.contract.jackson.group.conversations.ConversationHistoryResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationHistoryResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationHistoryResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsHistoryMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultConversationsHistoryMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ConversationsHistoryMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationHistoryResponse, ErrorConversationHistoryResponse> {
        val response = SlackRequestBuilder<ConversationHistoryResponse>(authToken, restTemplate)
                .with(params)
                .toMethod("conversations.history")
                .returnAsType(ConversationHistoryResponse::class.java)
                .postUrlEncoded(params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulConversationHistoryResponse -> {
                val responseEntity = response.body as SuccessfulConversationHistoryResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationHistoryResponse -> {
                val responseEntity = response.body as ErrorConversationHistoryResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}


