package com.kreait.slack.api.spring.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationSetTopicResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsMethodGroup
import com.kreait.slack.api.group.conversations.ConversationsSetTopicMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ConversationsMethodGroup.setTopic]
 */
@Suppress("UNCHECKED_CAST")
class SpringConversationsSetTopicMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : ConversationsSetTopicMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationSetTopicResponse, ErrorConversationSetTopicResponse> {

        val response = SlackRequestBuilder<ConversationSetTopicResponse>(authToken, restTemplate)
            .with(this.params)
            .toMethod("conversations.setTopic")
            .returnAsType(ConversationSetTopicResponse::class.java)
            .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulConversationSetTopicResponse -> {

                val responseEntity = response.body as SuccessfulConversationSetTopicResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorConversationSetTopicResponse -> {

                val responseEntity = response.body as ErrorConversationSetTopicResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
