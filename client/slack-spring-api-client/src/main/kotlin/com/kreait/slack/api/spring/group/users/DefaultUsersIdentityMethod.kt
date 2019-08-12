package com.kreait.slack.api.spring.group.users


import com.kreait.slack.api.contract.jackson.group.users.ErrorIdentityResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulIdentityResponse
import com.kreait.slack.api.contract.jackson.group.users.IdentityResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersIdentityMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultUsersIdentityMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsersIdentityMethod() {

    override fun request(): ApiCallResult<SuccessfulIdentityResponse, ErrorIdentityResponse> {
        val response = SlackRequestBuilder<IdentityResponse>(authToken, restTemplate)
                .toMethod("users.identity")
                .returnAsType(IdentityResponse::class.java)
                .postUrlEncoded(mapOf())

        return when (response.body!!) {
            is SuccessfulIdentityResponse -> {
                val responseEntity = response.body as SuccessfulIdentityResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorIdentityResponse -> {
                val responseEntity = response.body as ErrorIdentityResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
