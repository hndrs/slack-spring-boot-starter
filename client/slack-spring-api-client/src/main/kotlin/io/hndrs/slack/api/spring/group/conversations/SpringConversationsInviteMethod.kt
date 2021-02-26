package io.hndrs.slack.api.spring.group.conversations


import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationInviteResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationInviteResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationInviteResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsInviteMethod
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ConversationsMethodGroup.invite]
 */
@Suppress("UNCHECKED_CAST")
class SpringConversationsInviteMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.conversations.ConversationsInviteMethod() {

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationInviteResponse, ErrorConversationInviteResponse> {
        val response = SlackRequestBuilder<ConversationInviteResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("conversations.invite")
            .returnAsType(ConversationInviteResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationInviteResponse -> {
                val responseEntity = response.body as SuccessfulConversationInviteResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }
            is ErrorConversationInviteResponse -> {
                val responseEntity = response.body as ErrorConversationInviteResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}


