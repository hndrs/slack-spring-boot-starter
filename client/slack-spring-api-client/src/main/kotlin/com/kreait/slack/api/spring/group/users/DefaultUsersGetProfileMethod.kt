package com.kreait.slack.api.spring.group.users


import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersGetProfileMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.UsersGetProfileResponse
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultUsersGetProfileMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsersGetProfileMethod() {

    override fun request(): ApiCallResult<SuccessfulUsersGetProfileResponse, ErrorUsersGetProfileResponse> {
        val response = SlackRequestBuilder<UsersGetProfileResponse>(authToken, restTemplate)
                .toMethod("users.profile.get")
                .returnAsType(UsersGetProfileResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulUsersGetProfileResponse -> {
                val responseEntity = response.body as SuccessfulUsersGetProfileResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorUsersGetProfileResponse -> {
                val responseEntity = response.body as ErrorUsersGetProfileResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
