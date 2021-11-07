package io.hndrs.slack.api.spring.group.conversations


import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationHistoryResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationHistoryResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationHistoryResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsHistoryMethod
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ConversationsMethodGroup.history]
 */
@Suppress("UNCHECKED_CAST")
class SpringConversationsHistoryMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : ConversationsHistoryMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationHistoryResponse, ErrorConversationHistoryResponse> {
        val response = SlackRequestBuilder<ConversationHistoryResponse>(authToken, restTemplate)
            .with(params)
            .toMethod("conversations.history")
            .returnAsType(ConversationHistoryResponse::class.java)
            .postUrlEncoded(params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulConversationHistoryResponse -> {
                val responseEntity = response.body as SuccessfulConversationHistoryResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationHistoryResponse -> {
                val responseEntity = response.body as ErrorConversationHistoryResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}


