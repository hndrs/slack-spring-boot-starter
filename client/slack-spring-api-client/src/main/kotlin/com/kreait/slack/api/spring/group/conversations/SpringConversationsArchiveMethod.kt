package com.kreait.slack.api.spring.group.conversations


import com.kreait.slack.api.contract.jackson.group.conversations.ConversationArchiveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationArchiveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationArchiveResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsArchiveMethod
import com.kreait.slack.api.group.conversations.ConversationsMethodGroup
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ConversationsMethodGroup.archive]
 */
@Suppress("UNCHECKED_CAST")
class SpringConversationsArchiveMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ConversationsArchiveMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationArchiveResponse, ErrorConversationArchiveResponse> {
        val response = SlackRequestBuilder<ConversationArchiveResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("conversations.archive")
                .returnAsType(ConversationArchiveResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationArchiveResponse -> {
                val responseEntity = response.body as SuccessfulConversationArchiveResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationArchiveResponse -> {
                val responseEntity = response.body as ErrorConversationArchiveResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
