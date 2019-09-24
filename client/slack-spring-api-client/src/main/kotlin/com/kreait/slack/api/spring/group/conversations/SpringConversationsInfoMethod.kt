package com.kreait.slack.api.spring.group.conversations


import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsInfoResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationsInfoResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationsInfoResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsInfoMethod
import com.kreait.slack.api.group.conversations.ConversationsMethodGroup
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ConversationsMethodGroup.info]
 */
@Suppress("UNCHECKED_CAST")
class SpringConversationsInfoMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ConversationsInfoMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationsInfoResponse, ErrorConversationsInfoResponse> {
        val response = SlackRequestBuilder<ConversationsInfoResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("conversations.info")
                .returnAsType(ConversationsInfoResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulConversationsInfoResponse -> {
                val responseEntity = response.body as SuccessfulConversationsInfoResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationsInfoResponse -> {
                val responseEntity = response.body as ErrorConversationsInfoResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}


