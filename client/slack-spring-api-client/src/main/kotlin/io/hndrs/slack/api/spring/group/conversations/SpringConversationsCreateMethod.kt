package io.hndrs.slack.api.spring.group.conversations


import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationCreateResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationCreateResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationCreateResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsCreateMethod
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ConversationsMethodGroup.create]
 */
@Suppress("UNCHECKED_CAST")
class SpringConversationsCreateMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : ConversationsCreateMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationCreateResponse, ErrorConversationCreateResponse> {
        val response = SlackRequestBuilder<ConversationCreateResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("conversations.create")
            .returnAsType(ConversationCreateResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationCreateResponse -> {
                val responseEntity = response.body as SuccessfulConversationCreateResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationCreateResponse -> {
                val responseEntity = response.body as ErrorConversationCreateResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}


