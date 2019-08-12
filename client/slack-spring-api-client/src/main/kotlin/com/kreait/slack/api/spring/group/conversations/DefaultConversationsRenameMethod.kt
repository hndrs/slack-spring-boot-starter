package com.kreait.slack.api.spring.group.conversations


import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsRenameMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsRenameResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationsRenameResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationsRenameResponse
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultConversationsRenameMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ConversationsRenameMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationsRenameResponse, ErrorConversationsRenameResponse> {
        val response = SlackRequestBuilder<ConversationsRenameResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("conversations.rename")
                .returnAsType(ConversationsRenameResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationsRenameResponse -> {
                val responseEntity = response.body as SuccessfulConversationsRenameResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationsRenameResponse -> {
                val responseEntity = response.body as ErrorConversationsRenameResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}


