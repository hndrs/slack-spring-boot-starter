package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsJoinMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.conversations.ConversationJoinResponse
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationJoinResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationJoinResponse
import org.springframework.web.client.RestTemplate

@Suppress("UNCHECKED_CAST")
class DefaultConversationsJoinMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ConversationsJoinMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationJoinResponse, ErrorConversationJoinResponse> {
        val response = SlackRequestBuilder<ConversationJoinResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("conversations.join")
                .returnAsType(ConversationJoinResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationJoinResponse -> {
                val responseEntity = response.body as SuccessfulConversationJoinResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationJoinResponse -> {
                val responseEntity = response.body as ErrorConversationJoinResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }

    }


}