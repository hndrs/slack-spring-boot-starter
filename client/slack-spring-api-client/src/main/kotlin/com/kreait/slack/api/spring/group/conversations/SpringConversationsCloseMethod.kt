package com.kreait.slack.api.spring.group.conversations


import com.kreait.slack.api.contract.jackson.group.conversations.ConversationCloseResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationCloseResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationCloseResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsCloseMethod
import com.kreait.slack.api.group.conversations.ConversationsMethodGroup
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ConversationsMethodGroup.close]
 */
@Suppress("UNCHECKED_CAST")
class SpringConversationsCloseMethod constructor(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : ConversationsCloseMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationCloseResponse, ErrorConversationCloseResponse> {
        val response = SlackRequestBuilder<ConversationCloseResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("conversations.close")
            .returnAsType(ConversationCloseResponse::class.java)
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
