package io.olaph.slack.client.spring.group.conversations


import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsHistoryMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.conversations.ConversationHistoryResponse
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationHistoryResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationHistoryResponse
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultConversationsHistoryMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ConversationsHistoryMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationHistoryResponse, ErrorConversationHistoryResponse> {
        val response = SlackRequestBuilder<ConversationHistoryResponse>(authToken, restTemplate)
                .with(params)
                .toMethod("conversations.history")
                .returnAsType(ConversationHistoryResponse::class.java)
                .postUrlEncoded(params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulConversationHistoryResponse -> {
                val responseEntity = response.body as SuccessfulConversationHistoryResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationHistoryResponse -> {
                val responseEntity = response.body as ErrorConversationHistoryResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}


