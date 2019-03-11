package io.olaph.slack.client.implementation.group.auth

import io.olaph.slack.client.UnknownResponseException
import io.olaph.slack.client.implementation.group.SlackRequestBuilder
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.auth.AuthTestMethod
import io.olaph.slack.dto.jackson.group.auth.ErrorAuthResponse
import io.olaph.slack.dto.jackson.group.auth.SlackAuthResponse
import io.olaph.slack.dto.jackson.group.auth.SuccessfulAuthResponse

@Suppress("UNCHECKED_CAST")
class DefaultTestMethod(private val authToken: String) : AuthTestMethod() {

    override fun request(): ApiCallResult<SuccessfulAuthResponse, ErrorAuthResponse> {
        val response = SlackRequestBuilder<SlackAuthResponse>(authToken)
                .toMethod("auth.test")
                .returnAsType(SlackAuthResponse::class.java)
                .postWithJsonBody()

        return when {
            response.body is SuccessfulAuthResponse -> {
                val responseEntity = response.body as SuccessfulAuthResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            response.body is ErrorAuthResponse -> {
                val responseEntity = response.body as ErrorAuthResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
            else -> {
                throw UnknownResponseException(this::class, response)
            }
        }

    }
}
