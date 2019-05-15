package io.olaph.slack.client.spring.group.users

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.users.UsersGetPresenceMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.users.ErrorUsersGetPresenceResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersGetPresenceResponse
import io.olaph.slack.dto.jackson.group.users.UsersGetPresenceResponse
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