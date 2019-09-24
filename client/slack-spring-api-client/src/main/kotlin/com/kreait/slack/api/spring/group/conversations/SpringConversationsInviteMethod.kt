package com.kreait.slack.api.spring.group.conversations


import com.kreait.slack.api.contract.jackson.group.conversations.ConversationInviteResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationInviteResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationInviteResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsInviteMethod
import com.kreait.slack.api.group.conversations.ConversationsMethodGroup
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ConversationsMethodGroup.invite]
 */
@Suppress("UNCHECKED_CAST")
class SpringConversationsInviteMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ConversationsInviteMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationInviteResponse, ErrorConversationInviteResponse> {
        val response = SlackRequestBuilder<ConversationInviteResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("conversations.invite")
                .returnAsType(ConversationInviteResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationInviteResponse -> {
                val responseEntity = response.body as SuccessfulConversationInviteResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationInviteResponse -> {
                val responseEntity = response.body as ErrorConversationInviteResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}


