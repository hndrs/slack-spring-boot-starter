package io.hndrs.slack.api.spring.group.users

import io.hndrs.slack.api.contract.jackson.group.users.ErrorGetPresenceResponse
import io.hndrs.slack.api.contract.jackson.group.users.GetPresenceResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulGetPresenceResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.users.UsersGetPresenceMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [UsersMethodGroup.getPresence]
 */
@Suppress("UNCHECKED_CAST")
class SpringUsersGetPresenceMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()
) : UsersGetPresenceMethod() {

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
