package com.kreait.slack.api.spring.group.users


import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersIdentityResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersIdentityResponse
import com.kreait.slack.api.contract.jackson.group.users.UsersIdentityResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersIdentityMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultUsersIdentityMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsersIdentityMethod() {

    override fun request(): ApiCallResult<SuccessfulUsersIdentityResponse, ErrorUsersIdentityResponse> {
        val response = SlackRequestBuilder<UsersIdentityResponse>(authToken, restTemplate)
                .toMethod("users.identity")
                .returnAsType(UsersIdentityResponse::class.java)
                .postUrlEncoded(mapOf())

        return when (response.body!!) {
            is SuccessfulUsersIdentityResponse -> {
                val responseEntity = response.body as SuccessfulUsersIdentityResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorUsersIdentityResponse -> {
                val responseEntity = response.body as ErrorUsersIdentityResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
