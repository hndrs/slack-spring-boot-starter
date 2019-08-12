package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersSetPresenceMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersSetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersSetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.UsersSetPresenceResponse
import org.springframework.web.client.RestTemplate

/**
 * https://api.slack.com/methods/users.setPresence
 */
@Suppress("UNCHECKED_CAST")
class DefaultUsersSetPresenceMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsersSetPresenceMethod() {

    override fun request(): ApiCallResult<SuccessfulUsersSetPresenceResponse, ErrorUsersSetPresenceResponse> {
        val response = SlackRequestBuilder<UsersSetPresenceResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("users.setPresence")
                .returnAsType(UsersSetPresenceResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulUsersSetPresenceResponse -> {
                val responseEntity = response.body as SuccessfulUsersSetPresenceResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorUsersSetPresenceResponse -> {
                val responseEntity = response.body as ErrorUsersSetPresenceResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
