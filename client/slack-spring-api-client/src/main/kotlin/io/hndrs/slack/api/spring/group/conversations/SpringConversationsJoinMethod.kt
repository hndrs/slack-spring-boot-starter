package io.hndrs.slack.api.spring.group.conversations

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationJoinResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationJoinResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationJoinResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsJoinMethod
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ConversationsMethodGroup.join]
 */
@Suppress("UNCHECKED_CAST")
class SpringConversationsJoinMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.conversations.ConversationsJoinMethod() {

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationJoinResponse, ErrorConversationJoinResponse> {
        val response = SlackRequestBuilder<ConversationJoinResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("conversations.join")
            .returnAsType(ConversationJoinResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationJoinResponse -> {
                val responseEntity = response.body as SuccessfulConversationJoinResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }
            is ErrorConversationJoinResponse -> {
                val responseEntity = response.body as ErrorConversationJoinResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }

    }


}
