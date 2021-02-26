package io.hndrs.slack.api.spring.group.conversations


import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationRepliesResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationRepliesResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationRepliesResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.group.conversations.ConversationsRepliesMethod
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ConversationsMethodGroup.replies]
 */
@Suppress("UNCHECKED_CAST")
class SpringConversationsRepliesMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.conversations.ConversationsRepliesMethod() {

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationRepliesResponse, ErrorConversationRepliesResponse> {
        val response = SlackRequestBuilder<ConversationRepliesResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("conversations.replies")
            .returnAsType(ConversationRepliesResponse::class.java)
            .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulConversationRepliesResponse -> {
                val responseEntity = response.body as SuccessfulConversationRepliesResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }
            is ErrorConversationRepliesResponse -> {
                val responseEntity = response.body as ErrorConversationRepliesResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}


