package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorSetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulSetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.SetPresenceResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersSetPresenceMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * https://api.slack.com/methods/users.setPresence
 */
@Suppress("UNCHECKED_CAST")
class DefaultUsersSetPresenceMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsersSetPresenceMethod() {

    override fun request(): ApiCallResult<SuccessfulSetPresenceResponse, ErrorSetPresenceResponse> {
        val response = SlackRequestBuilder<SetPresenceResponse>(authToken, restTemplate)
                .with(this.params)
                .toMethod("users.setPresence")
                .returnAsType(SetPresenceResponse::class.java)
                .postWithJsonBody()

        return when (response.body!!) {
            is SuccessfulSetPresenceResponse -> {
                val responseEntity = response.body as SuccessfulSetPresenceResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorSetPresenceResponse -> {
                val responseEntity = response.body as ErrorSetPresenceResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
