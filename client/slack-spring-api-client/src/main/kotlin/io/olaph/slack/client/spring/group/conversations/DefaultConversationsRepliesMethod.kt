package io.olaph.slack.client.spring.group.conversations


import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsRepliesMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.conversations.ConversationRepliesResponse
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationRepliesResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationRepliesResponse
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultConversationsRepliesMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ConversationsRepliesMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationRepliesResponse, ErrorConversationRepliesResponse> {
        val response = SlackRequestBuilder<ConversationRepliesResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("conversations.replies")
                .returnAsType(ConversationRepliesResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulConversationRepliesResponse -> {
                val responseEntity = response.body as SuccessfulConversationRepliesResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationRepliesResponse -> {
                val responseEntity = response.body as ErrorConversationRepliesResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}


