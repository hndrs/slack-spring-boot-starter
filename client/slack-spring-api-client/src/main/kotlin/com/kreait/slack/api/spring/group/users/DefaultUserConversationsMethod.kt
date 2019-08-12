package com.kreait.slack.api.spring.group.users


import com.kreait.slack.api.contract.jackson.group.users.ErrorUserConversationsResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUserConversationsResponse
import com.kreait.slack.api.contract.jackson.group.users.UserConversationsResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UserConversationsMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultUserConversationsMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UserConversationsMethod() {

    override fun request(): ApiCallResult<SuccessfulUserConversationsResponse, ErrorUserConversationsResponse> {
        val response = SlackRequestBuilder<UserConversationsResponse>(authToken, restTemplate)
                .toMethod("users.conversations")
                .returnAsType(UserConversationsResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulUserConversationsResponse -> {
                val responseEntity = response.body as SuccessfulUserConversationsResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorUserConversationsResponse -> {
                val responseEntity = response.body as ErrorUserConversationsResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
