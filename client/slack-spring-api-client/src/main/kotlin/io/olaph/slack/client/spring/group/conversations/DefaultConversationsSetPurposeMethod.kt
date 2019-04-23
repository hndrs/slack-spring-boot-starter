package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsSetPurposeMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.conversations.ConversationSetPurposeResponse
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationSetPurposeResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationSetPurposeResponse
import org.springframework.web.client.RestTemplate

@Suppress("UNCHECKED_CAST")
class DefaultConversationsSetPurposeMethod(private val authToken: String,
                                           private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate())
    : ConversationsSetPurposeMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationSetPurposeResponse, ErrorConversationSetPurposeResponse> {

        val response = SlackRequestBuilder<ConversationSetPurposeResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("conversations.setPurpose")
                .returnAsType(ConversationSetPurposeResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationSetPurposeResponse -> {

                val responseEntity = response.body as SuccessfulConversationSetPurposeResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorConversationSetPurposeResponse -> {

                val responseEntity = response.body as ErrorConversationSetPurposeResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}