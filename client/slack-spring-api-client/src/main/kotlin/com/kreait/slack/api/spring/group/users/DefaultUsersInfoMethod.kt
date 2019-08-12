package com.kreait.slack.api.spring.group.users


import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.users.UsersInfoMethod
import com.kreait.slack.api.spring.group.RestTemplateFactory
import com.kreait.slack.api.spring.group.SlackRequestBuilder
import com.kreait.slack.api.contract.jackson.group.users.ErrorUsersInfoResponse
import com.kreait.slack.api.contract.jackson.group.users.SlackInfoResponse
import com.kreait.slack.api.contract.jackson.group.users.SuccessfulUsersInfoResponse
import org.springframework.web.client.RestTemplate


@Suppress("UNCHECKED_CAST")
class DefaultUsersInfoMethod(private val authToken: String, private val restTemplate: RestTemplate = RestTemplateFactory.slackTemplate()) : UsersInfoMethod() {

    override fun request(): ApiCallResult<SuccessfulUsersInfoResponse, ErrorUsersInfoResponse> {
        val response = SlackRequestBuilder<SlackInfoResponse>(authToken, restTemplate)
                .toMethod("users.info")
                .returnAsType(SlackInfoResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when (response.body!!) {
            is SuccessfulUsersInfoResponse -> {
                val responseEntity = response.body as SuccessfulUsersInfoResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            is ErrorUsersInfoResponse -> {
                val responseEntity = response.body as ErrorUsersInfoResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
        }
    }
}
