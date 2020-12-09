
package com.kreait.slack.api.spring.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationJoinResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationJoinResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationJoinResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsJoinMethod
import com.kreait.slack.api.group.conversations.ConversationsMethodGroup
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ConversationsMethodGroup.join]
 */
@Suppress("UNCHECKED_CAST")
class SpringConversationsJoinMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : ConversationsJoinMethod() {

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
