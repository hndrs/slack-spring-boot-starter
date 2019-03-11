package io.olaph.slack.client.implementation.group.users

import io.olaph.slack.client.UnknownResponseException
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.users.UsersInfoMethod
import io.olaph.slack.client.implementation.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.users.ErrorInfoResponse
import io.olaph.slack.dto.jackson.group.users.SlackInfoResponse
import io.olaph.slack.dto.jackson.group.users.SuccessfulInfoResponse

@Suppress("UNCHECKED_CAST")
class DefaultUsersInfoMethod(private val authToken: String) : UsersInfoMethod() {

    override fun request(): ApiCallResult<SuccessfulInfoResponse, ErrorInfoResponse> {
        val response = SlackRequestBuilder<SlackInfoResponse>(authToken)
                .toMethod("users.info")
                .returnAsType(SlackInfoResponse::class.java)
                .postUrlEncoded(this.params.toRequestMap())

        return when {
            response.body is SuccessfulInfoResponse -> {
                val responseEntity = response.body as SuccessfulInfoResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            response.body is ErrorInfoResponse -> {
                val responseEntity = response.body as ErrorInfoResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
            else -> {
                throw UnknownResponseException(this::class, response)
            }
        }
    }
}
