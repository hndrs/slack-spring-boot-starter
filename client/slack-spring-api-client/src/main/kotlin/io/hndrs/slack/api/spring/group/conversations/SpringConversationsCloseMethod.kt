package io.hndrs.slack.api.spring.group.conversations


import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationCloseResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationCloseResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationCloseResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsCloseMethod
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ConversationsMethodGroup.close]
 */
@Suppress("UNCHECKED_CAST")
class SpringConversationsCloseMethod constructor(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.conversations.ConversationsCloseMethod() {

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationCloseResponse, ErrorConversationCloseResponse> {
        val response = SlackRequestBuilder<ConversationCloseResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("conversations.close")
            .returnAsType(ConversationCloseResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationCloseResponse -> {
                val responseEntity = response.body as SuccessfulConversationCloseResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }
            is ErrorConversationCloseResponse -> {
                val responseEntity = response.body as ErrorConversationCloseResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}
