package io.olaph.slack.client.spring.group.conversations


import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsListMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.conversations.ConversationListResponse
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationListResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationListResponse
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultConversationsListMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ConversationsListMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationListResponse, ErrorConversationListResponse> {
        val response = SlackRequestBuilder<ConversationListResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("conversations.list")
                .returnAsType(ConversationListResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulConversationListResponse -> {
                val responseEntity = response.body as SuccessfulConversationListResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationListResponse -> {
                val responseEntity = response.body as ErrorConversationListResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}


