package com.kreait.slack.api.spring.group.users

/**
 * https://api.slack.com/methods/users.lookupByEmail
 */
import com.kreait.slack.api.contract.jackson.group.users.ErrorLookupByEmailResponse
import com.kreait.slack.api.contract.jackson.group.users.LookupByEmailResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulLookupByEmailResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersLookupByEmailMethod
import com.kreait.slack.api.group.users.UsersMethodGroup
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


/**
 * Spring based implementation of [UsersMethodGroup.lookupByEmail]
 */
@Suppress("UNCHECKED_CAST")
class SpringUsersLookupByEmailMethod(private val authToken: String, val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsersLookupByEmailMethod() {

    override fun request(): ApiCallResult<SuccessfulLookupByEmailResponse, ErrorLookupByEmailResponse> {

        val response = SlackRequestBuilder<LookupByEmailResponse>(authToken, restTemplate)
                .toMethod("users.lookupByEmail")
                .returnAsType(LookupByEmailResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulLookupByEmailResponse -> {
                val responseEntity = response.body as SuccessfulLookupByEmailResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorLookupByEmailResponse -> {
                val responseEntity = response.body as ErrorLookupByEmailResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
