package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.ErrorResponseException
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsCloseMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationCloseResponse
import io.olaph.slack.dto.jackson.group.conversations.SlackConversationCloseResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationCloseResponse
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultConversationsCloseMethod constructor(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ConversationsCloseMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationCloseResponse, ErrorConversationCloseResponse> {
        val response = SlackRequestBuilder<SlackConversationCloseResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("conversations.close")
                .returnAsType(SlackConversationCloseResponse::class.java)
                .postWithJsonBody()

        if (!response.statusCode.is2xxSuccessful) {
            throw ErrorResponseException(this::class, response.statusCode.name)
        }

        return when (response.body!!) {
            is SuccessfulConversationCloseResponse -> {
                val responseEntity = response.body as SuccessfulConversationCloseResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationCloseResponse -> {
                val responseEntity = response.body as ErrorConversationCloseResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
