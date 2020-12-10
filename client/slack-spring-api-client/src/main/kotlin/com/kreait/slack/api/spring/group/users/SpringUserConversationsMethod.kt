package com.kreait.slack.api.spring.group.users


import com.kreait.slack.api.contract.jackson.group.users.ConversationsResponse
import com.kreait.slack.api.contract.jackson.group.users.ErrorConversationsResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulConversationsResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UserConversationsMethod
import com.kreait.slack.api.group.users.UsersMethodGroup
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * Spring based implementation of [UsersMethodGroup.conversations]
 */
@Suppress("UNCHECKED_CAST")
class SpringUserConversationsMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : UserConversationsMethod() {

    override fun request(): ApiCallResult<SuccessfulConversationsResponse, ErrorConversationsResponse> {
        val response = SlackRequestBuilder<ConversationsResponse>(authToken, restTemplate)
            .toMethod("users.conversations")
            .returnAsType(ConversationsResponse::class.java)
            .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulConversationsResponse -> {
                val responseEntity = response.body as SuccessfulConversationsResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorConversationsResponse -> {
                val responseEntity = response.body as ErrorConversationsResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
