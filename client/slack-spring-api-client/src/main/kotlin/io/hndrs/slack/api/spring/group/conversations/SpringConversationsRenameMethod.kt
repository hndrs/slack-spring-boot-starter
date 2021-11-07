package io.hndrs.slack.api.spring.group.conversations


import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationsRenameResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationsRenameResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationsRenameResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.group.conversations.ConversationsRenameMethod
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ConversationsMethodGroup.rename]
 */
@Suppress("UNCHECKED_CAST")
class SpringConversationsRenameMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : ConversationsRenameMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationsRenameResponse, ErrorConversationsRenameResponse> {
        val response = SlackRequestBuilder<ConversationsRenameResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("conversations.rename")
            .returnAsType(ConversationsRenameResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationsRenameResponse -> {
                val responseEntity = response.body as SuccessfulConversationsRenameResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationsRenameResponse -> {
                val responseEntity = response.body as ErrorConversationsRenameResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}


