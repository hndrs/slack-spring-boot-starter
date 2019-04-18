package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.ErrorResponseException
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsMembersMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.conversations.ConversationMembersResponse
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationMembersResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationMembersResponse
import org.springframework.web.client.RestTemplate


/**
 * https://api.slack.com/methods/conversations.members
 */
@Suppress("UNCHECKED_CAST")
class DefaultConversationsMembersMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ConversationsMembersMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationMembersResponse, ErrorConversationMembersResponse> {
        val response = SlackRequestBuilder<ConversationMembersResponse>(authToken, restTemplate)
                .toMethod("conversations.members")
                .returnAsType(ConversationMembersResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        if (!response.statusCode.is2xxSuccessful) {
            throw ErrorResponseException(this::class, response.statusCode.name)
        }

        return when (response.body!!) {
            is SuccessfulConversationMembersResponse -> {
                val responseEntity = response.body as SuccessfulConversationMembersResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationMembersResponse -> {
                val responseEntity = response.body as ErrorConversationMembersResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
