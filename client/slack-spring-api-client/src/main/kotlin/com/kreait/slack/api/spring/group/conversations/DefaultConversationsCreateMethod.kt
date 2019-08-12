package com.kreait.slack.api.spring.group.conversations


import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationCreateResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationCreateResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationCreateResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsCreateMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultConversationsCreateMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ConversationsCreateMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationCreateResponse, ErrorConversationCreateResponse> {
        val response = SlackRequestBuilder<ConversationCreateResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("conversations.create")
                .returnAsType(ConversationCreateResponse::class.java)
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


