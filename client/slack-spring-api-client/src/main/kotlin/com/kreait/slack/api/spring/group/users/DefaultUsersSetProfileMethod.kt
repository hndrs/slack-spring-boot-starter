package com.kreait.slack.api.spring.group.users


import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersSetProfileMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersSetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersSetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.UsersSetProfileResponse
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultUsersSetProfileMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsersSetProfileMethod() {

    override fun request(): ApiCallResult<SuccessfulUsersSetProfileResponse, ErrorUsersSetProfileResponse> {
        val response = SlackRequestBuilder<UsersSetProfileResponse>(authToken, restTemplate)
                .toMethod("users.profile.set")
                .returnAsType(UsersSetProfileResponse::class.java)
                .postUrlEncoded(mapOf())

        return when (response.body!!) {
            is SuccessfulUsersSetProfileResponse -> {
                val responseEntity = response.body as SuccessfulUsersSetProfileResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorUsersSetProfileResponse -> {
                val responseEntity = response.body as ErrorUsersSetProfileResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
