package io.hndrs.slack.api.spring.group.conversations

import io.hndrs.slack.api.contract.jackson.group.conversations.ConversationSetPurposeResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.ErrorConversationSetPurposeResponse
import io.hndrs.slack.api.contract.jackson.group.conversations.SuccessfulConversationSetPurposeResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.conversations.ConversationsMethodGroup
import io.hndrs.slack.api.group.conversations.ConversationsSetPurposeMethod
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ConversationsMethodGroup.setPurpose]
 */
@Suppress("UNCHECKED_CAST")
class SpringConversationsSetPurposeMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.conversations.ConversationsSetPurposeMethod() {

    override fun request():
            io.hndrs.slack.api.group.ApiCallResult<SuccessfulConversationSetPurposeResponse, ErrorConversationSetPurposeResponse> {

        val response = SlackRequestBuilder<ConversationSetPurposeResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("conversations.setPurpose")
            .returnAsType(ConversationSetPurposeResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationSetPurposeResponse -> {

                val responseEntity = response.body as SuccessfulConversationSetPurposeResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }

            is ErrorConversationSetPurposeResponse -> {

                val responseEntity = response.body as ErrorConversationSetPurposeResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}
