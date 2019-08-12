package com.kreait.slack.api.spring.group.conversations


import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsLeaveMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsLeaveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationLeaveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationLeaveResponse
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


