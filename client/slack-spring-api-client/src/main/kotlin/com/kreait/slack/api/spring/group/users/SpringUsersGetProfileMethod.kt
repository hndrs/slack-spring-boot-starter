package com.kreait.slack.api.spring.group.users


import com.kreait.slack.api.contract.jackson.group.users.ErrorGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulGetProfileResponse
import com.kreait.slack.api.contract.jackson.group.users.GetProfileResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersGetProfileMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class SpringUsersGetProfileMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsersGetProfileMethod() {

    override fun request(): ApiCallResult<SuccessfulGetProfileResponse, ErrorGetProfileResponse> {
        val response = SlackRequestBuilder<GetProfileResponse>(authToken, restTemplate)
                .toMethod("users.profile.get")
                .returnAsType(GetProfileResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulGetProfileResponse -> {
                val responseEntity = response.body as SuccessfulGetProfileResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorGetProfileResponse -> {
                val responseEntity = response.body as ErrorGetProfileResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
