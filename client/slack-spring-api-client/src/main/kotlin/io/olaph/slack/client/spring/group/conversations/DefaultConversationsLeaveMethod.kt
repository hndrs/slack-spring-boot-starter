package io.olaph.slack.client.spring.group.conversations

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.conversations.ConversationsLeaveMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.conversations.ConversationsLeaveResponse
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationLeaveResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationLeaveResponse
import org.springframework.web.client.RestTemplate

@Suppress("UNCHECKED_CAST")
class DefaultConversationsLeaveMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ConversationsLeaveMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationLeaveResponse, ErrorConversationLeaveResponse> {
        val response = SlackRequestBuilder<ConversationsLeaveResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("conversations.leave")
                .returnAsType(ConversationsLeaveResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationLeaveResponse -> {
                val responseEntity = response.body as SuccessfulConversationLeaveResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationLeaveResponse -> {
                val responseEntity = response.body as ErrorConversationLeaveResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}


