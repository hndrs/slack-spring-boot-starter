package com.kreait.slack.api.spring.group.conversations

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsSetPurposeMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationSetPurposeResponse
import org.springframework.web.client.RestTemplate

@Suppress("UNCHECKED_CAST")
class DefaultConversationsSetPurposeMethod(private val authToken: String,
                                           private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate())
    : ConversationsSetPurposeMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationSetPurposeResponse, ErrorConversationSetPurposeResponse> {

        val response = SlackRequestBuilder<ConversationSetPurposeResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("conversations.setPurpose")
                .returnAsType(ConversationSetPurposeResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationSetPurposeResponse -> {

                val responseEntity = response.body as SuccessfulConversationSetPurposeResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorConversationSetPurposeResponse -> {

                val responseEntity = response.body as ErrorConversationSetPurposeResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
