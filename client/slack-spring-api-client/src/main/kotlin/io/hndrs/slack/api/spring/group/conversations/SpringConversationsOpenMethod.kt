package io.hndrs.slack.api.spring.group.conversations


import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationOpenResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationOpenResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationOpenResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.group.conversations.ConversationsOpenMethod
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ConversationsMethodGroup.open]
 */
@Suppress("UNCHECKED_CAST")
class SpringConversationsOpenMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.conversations.ConversationsOpenMethod() {

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationOpenResponse, ErrorConversationOpenResponse> {
        val response = SlackRequestBuilder<ConversationOpenResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("conversations.open")
            .returnAsType(ConversationOpenResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationOpenResponse -> {
                val responseEntity = response.body as SuccessfulConversationOpenResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }
            is ErrorConversationOpenResponse -> {
                val responseEntity = response.body as ErrorConversationOpenResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}
