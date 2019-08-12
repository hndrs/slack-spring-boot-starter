package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersGetPresenceMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersGetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersGetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.UsersGetPresenceResponse
import org.springframework.web.client.RestTemplate

/**
 * https://api.slack.com/methods/users.getPresence
 */
class DefaultUsersGetPresenceMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsersGetPresenceMethod() {

    override fun request(): ApiCallResult<SuccessfulUsersGetPresenceResponse, ErrorUsersGetPresenceResponse> {
        val response = SlackRequestBuilder<UsersGetPresenceResponse>(authToken, restTemplate)
                .toMethod("users.getPresence")
                .returnAsType(UsersGetPresenceResponse::class.java)
                .with(this.params)
                .postUrlEncoded(mapOf())

        return when (response.body!!) {

            is SuccessfulUsersGetPresenceResponse -> {

                val responseEntity = response.body as SuccessfulUsersGetPresenceResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorUsersGetPresenceResponse -> {

                val responseEntity = response.body as ErrorUsersGetPresenceResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
