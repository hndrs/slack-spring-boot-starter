package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.UnknownResponseException
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsArchiveMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.conversations.ConversationArchiveResponse
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationArchiveResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationArchiveResponse
import org.springframework.web.client.RestTemplate

@Suppress("UNCHECKED_CAST")
class DefaultConversationsArchiveMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ConversationsArchiveMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationArchiveResponse, ErrorConversationArchiveResponse> {
        val response = SlackRequestBuilder<ConversationArchiveResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("conversations.archive")
                .returnAsType(ConversationArchiveResponse::class.java)
                .postWithJsonBody()

        return when {
            response.body is SuccessfulConversationArchiveResponse -> {
                val responseEntity = response.body as SuccessfulConversationArchiveResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            response.body is ErrorConversationArchiveResponse -> {
                val responseEntity = response.body as ErrorConversationArchiveResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
            else -> {
                throw UnknownResponseException(this::class, response)
            }
        }
    }
}
