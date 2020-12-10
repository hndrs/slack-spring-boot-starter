package com.kreait.slack.api.spring.group.conversations


import com.kreait.slack.api.contract.jackson.group.conversations.ConversationOpenResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationOpenResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationOpenResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsMethodGroup
import com.kreait.slack.api.group.conversations.ConversationsOpenMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ConversationsMethodGroup.open]
 */
@Suppress("UNCHECKED_CAST")
class SpringConversationsOpenMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : ConversationsOpenMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationOpenResponse, ErrorConversationOpenResponse> {
        val response = SlackRequestBuilder<ConversationOpenResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("conversations.open")
            .returnAsType(ConversationOpenResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationOpenResponse -> {
                val responseEntity = response.body as SuccessfulConversationOpenResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationOpenResponse -> {
                val responseEntity = response.body as ErrorConversationOpenResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
