package io.hndrs.slack.api.spring.group.conversations

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsKickResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationKickResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationKickResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsKickMethod
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ConversationsMethodGroup.kick]
 */
@Suppress("UNCHECKED_CAST")
class SpringConversationsKickMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : ConversationsKickMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationKickResponse, ErrorConversationKickResponse> {
        val response = SlackRequestBuilder<ConversationsKickResponse>(authToken, restTemplate)
            .toMethod("conversations.kick")
            .returnAsType(ConversationsKickResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationKickResponse -> {
                val responseEntity = response.body as SuccessfulConversationKickResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorConversationKickResponse -> {
                val responseEntity = response.body as ErrorConversationKickResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
