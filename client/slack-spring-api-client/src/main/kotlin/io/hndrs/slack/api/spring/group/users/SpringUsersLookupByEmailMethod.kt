package io.hndrs.slack.api.spring.group.users

/**
 * https://api.slack.com/methods/users.lookupByEmail
 */
import io.hndrs.slack.api.contract.jackson.group.users.ErrorLookupByEmailResponse
import io.hndrs.slack.api.contract.jackson.group.users.LookupByEmailResponse
import io.hndrs.slack.api.contract.jackson.group.users.SuccessfulLookupByEmailResponse
import io.hndrs.slack.api.group.ApiCallResult
import io.hndrs.slack.api.group.users.UsersLookupByEmailMethod
import io.hndrs.slack.api.group.users.UsersMethodGroup
import io.hndrs.slack.api.spring.group.RestTemplateFactory
import io.hndrs.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [UsersMethodGroup.lookupByEmail]
 */
@Suppress("UNCHECKED_CAST")
class SpringUsersLookupByEmailMethod(
    private val authToken: String,
    val restTemplate: RestTemplate = io.hndrs.slack.api.spring.group.RestTemplateFactory.slackTemplate()
) : io.hndrs.slack.api.group.users.UsersLookupByEmailMethod() {

    override fun request(): io.hndrs.slack.api.group.ApiCallResult<SuccessfulLookupByEmailResponse, ErrorLookupByEmailResponse> {

        val response = SlackRequestBuilder<LookupByEmailResponse>(authToken, restTemplate)
            .toMethod("users.lookupByEmail")
            .returnAsType(LookupByEmailResponse::class.java)
            .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulLookupByEmailResponse -> {
                val responseEntity = response.body as SuccessfulLookupByEmailResponse
                this.onSuccess?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(success = responseEntity)
            }
            is ErrorLookupByEmailResponse -> {
                val responseEntity = response.body as ErrorLookupByEmailResponse
                this.onFailure?.invoke(responseEntity)
                io.hndrs.slack.api.group.ApiCallResult(failure = responseEntity)
            }
        }
    }
}
