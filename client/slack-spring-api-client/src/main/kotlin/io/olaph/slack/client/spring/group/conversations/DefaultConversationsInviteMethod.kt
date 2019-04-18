package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.ErrorResponseException
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsInviteMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.conversations.ConversationInviteResponse
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationInviteResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationInviteResponse
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultConversationsInviteMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ConversationsInviteMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationInviteResponse, ErrorConversationInviteResponse> {
        val response = SlackRequestBuilder<ConversationInviteResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("conversations.invite")
                .returnAsType(ConversationInviteResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationInviteResponse -> {
                val responseEntity = response.body as SuccessfulConversationInviteResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationInviteResponse -> {
                val responseEntity = response.body as ErrorConversationInviteResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}


