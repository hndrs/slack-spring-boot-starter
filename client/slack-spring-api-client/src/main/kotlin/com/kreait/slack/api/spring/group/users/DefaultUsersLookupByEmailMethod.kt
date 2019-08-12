package com.kreait.slack.api.spring.group.users

import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersLookupByEmailResponse
import com.kreait.slack.api.contract.jackson.group.users.SlackLookupByEmailResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersLookupByEmailResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersLookupByEmailMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate

/**
 * https://api.slack.com/methods/users.lookupByEmail
 */
@Suppress("UNCHECKED_CAST")
class DefaultUsersLookupByEmailMethod(private val authToken: String, val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsersLookupByEmailMethod() {

    override fun request(): ApiCallResult<SuccessfulUsersLookupByEmailResponse, ErrorUsersLookupByEmailResponse> {

        val response = SlackRequestBuilder<SlackLookupByEmailResponse>(authToken, restTemplate)
                .toMethod("users.lookupByEmail")
                .returnAsType(SlackLookupByEmailResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulUsersLookupByEmailResponse -> {
                val responseEntity = response.body as SuccessfulUsersLookupByEmailResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorUsersLookupByEmailResponse -> {
                val responseEntity = response.body as ErrorUsersLookupByEmailResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
