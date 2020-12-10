package com.kreait.slack.api.spring.group.conversations


/**
 * https://api.slack.com/methods/conversations.members
 */
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationMembersResponse
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationMembersResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationMembersResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.conversations.ConversationsMembersMethod
import com.kreait.slack.api.group.conversations.ConversationsMethodGroup
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [ConversationsMethodGroup.members]
 */
@Suppress("UNCHECKED_CAST")
class SpringConversationsMembersMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : ConversationsMembersMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationMembersResponse, ErrorConversationMembersResponse> {
        val response = SlackRequestBuilder<ConversationMembersResponse>(authToken, restTemplate)
            .toMethod("conversations.members")
            .returnAsType(ConversationMembersResponse::class.java)
            .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulConversationMembersResponse -> {
                val responseEntity = response.body as SuccessfulConversationMembersResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationMembersResponse -> {
                val responseEntity = response.body as ErrorConversationMembersResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
