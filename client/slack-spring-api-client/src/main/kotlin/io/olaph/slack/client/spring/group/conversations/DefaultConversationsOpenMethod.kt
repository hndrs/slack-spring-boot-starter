package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.ErrorResponseException
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsOpenMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.conversations.ConversationOpenResponse
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationOpenResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationOpenResponse
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultConversationsOpenMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ConversationsOpenMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationOpenResponse, ErrorConversationOpenResponse> {
        val response = SlackRequestBuilder<ConversationOpenResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("conversations.open")
                .returnAsType(ConversationOpenResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationOpenResponse -> {
                val responseEntity = response.body as SuccessfulConversationOpenResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationOpenResponse -> {
                val responseEntity = response.body as ErrorConversationOpenResponse
                if (!response.statusCode.is2xxSuccessful) {
                    throw ErrorResponseException(this::class, response.statusCode.name, responseEntity.error)
                }
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
