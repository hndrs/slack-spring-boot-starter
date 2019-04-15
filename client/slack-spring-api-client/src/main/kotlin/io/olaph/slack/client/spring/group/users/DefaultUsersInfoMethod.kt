package io.olaph.slack.client.spring.group.users

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.users.UsersInfoMethod
import io.olaph.slack.client.spring.group.RestTemplateFactory
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.users.ErrorUsersInfoResponse
import io.olaph.slack.dto.jackson.group.users.SlackInfoResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulUsersInfoResponse
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
