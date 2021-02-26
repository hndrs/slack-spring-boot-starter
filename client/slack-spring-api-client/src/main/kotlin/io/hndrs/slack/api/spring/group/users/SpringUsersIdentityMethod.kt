package io.hndrs.slack.api.spring.group.users


import io.hndrs.slack.api.contract.jackson.group.users.ErrorIdentityResponse
import io.hndrs.slack.api.contract.jackson.group.users.IdentityResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulIdentityResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.users.UsersIdentityMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [UsersMethodGroup.identity]
 */
@Suppress("UNCHECKED_CAST")
class SpringUsersIdentityMethod(
    private val authToken: String,
    private val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.users.UsersIdentityMethod() {

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulIdentityResponse, ErrorIdentityResponse> {
        val response = SlackRequestBuilder<IdentityResponse>(authToken, restTemplate)
            .toMethod("users.identity")
            .returnAsType(IdentityResponse::class.java)
            .postUrlEncoded(mapOf())

        return when (response.body!!) {
            is SuccessfulIdentityResponse -> {
                val responseEntity = response.body as SuccessfulIdentityResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }
            is ErrorIdentityResponse -> {
                val responseEntity = response.body as ErrorIdentityResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}
