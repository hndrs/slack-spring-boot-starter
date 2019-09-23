package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorGetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulGetPresenceResponse
import com.kreait.slack.api.contract.jackson.group.users.GetPresenceResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersGetPresenceMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * https://api.slack.com/methods/users.getPresence
 */
class SpringUsersGetPresenceMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsersGetPresenceMethod() {

    override fun request(): ApiCallResult<SuccessfulGetPresenceResponse, ErrorGetPresenceResponse> {
        val response = SlackRequestBuilder<GetPresenceResponse>(authToken, restTemplate)
                .toMethod("users.getPresence")
                .returnAsType(GetPresenceResponse::class.java)
                .with(this.params)
                .postUrlEncoded(mapOf())

        return when (response.body!!) {

            is SuccessfulGetPresenceResponse -> {

                val responseEntity = response.body as SuccessfulGetPresenceResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            is ErrorGetPresenceResponse -> {

                val responseEntity = response.body as ErrorGetPresenceResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
