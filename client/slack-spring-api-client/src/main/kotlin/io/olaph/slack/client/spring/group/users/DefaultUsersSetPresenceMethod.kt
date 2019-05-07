package io.olaph.slack.client.spring.group.users

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.users.UsersSetPresenceMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.users.ErrorUsersSetPresenceResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersSetPresenceResponse
import io.olaph.slack.dto.jackson.group.users.UsersSetPresenceResponse
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