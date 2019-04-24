package io.olaph.slack.client.spring.group.conversations


import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsInfoMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.conversations.ConversationsInfoResponse
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationsInfoResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationsInfoResponse
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultConversationsInfoMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ConversationsInfoMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationsInfoResponse, ErrorConversationsInfoResponse> {
        val response = SlackRequestBuilder<ConversationsInfoResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("conversations.info")
                .returnAsType(ConversationsInfoResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulConversationsInfoResponse -> {
                val responseEntity = response.body as SuccessfulConversationsInfoResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationsInfoResponse -> {
                val responseEntity = response.body as ErrorConversationsInfoResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}


