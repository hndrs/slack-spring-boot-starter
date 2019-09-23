package com.kreait.slack.api.spring.group.conversations


import com.kreait.slack.api.contract.jackson.group.conversations.ConversationUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationUnarchiveResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsUnarchiveMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

@Suppress("UNCHECKED_CAST")
class SpringConversationsUnarchiveMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ConversationsUnarchiveMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationUnarchiveResponse, ErrorConversationUnarchiveResponse> {
        val response = SlackRequestBuilder<ConversationUnarchiveResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("conversations.unarchive")
                .returnAsType(ConversationUnarchiveResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationUnarchiveResponse -> {
                val responseEntity = response.body as SuccessfulConversationUnarchiveResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationUnarchiveResponse -> {
                val responseEntity = response.body as ErrorConversationUnarchiveResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
