package io.hndrs.slack.api.spring.group.conversations


import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationListResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationListResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationListResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsListMethod
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ConversationsMethodGroup.list]
 */
@Suppress("UNCHECKED_CAST")
class SpringConversationsListMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : ConversationsListMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationListResponse, ErrorConversationListResponse> {
        val response = SlackRequestBuilder<ConversationListResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("conversations.list")
            .returnAsType(ConversationListResponse::class.java)
            .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulConversationListResponse -> {
                val responseEntity = response.body as SuccessfulConversationListResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationListResponse -> {
                val responseEntity = response.body as ErrorConversationListResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}


