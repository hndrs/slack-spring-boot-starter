package io.olaph.slack.client.implementation.group.im

import io.olaph.slack.client.UnknownResponseException
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.im.ImMarkMethod
import io.olaph.slack.client.implementation.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.im.ErrorImMarkResponse
import io.olaph.slack.dto.jackson.group.im.SlackImMarkResponse
import io.olaph.slack.dto.jackson.group.im.SuccessfulImMarkResponse

/**
 * https://api.slack.com/methods/im.mark
 */

@Suppress("UNCHECKED_CAST")
class DefaultImMarkMethod(private val authToken: String) : ImMarkMethod() {

    override fun request(): ApiCallResult<SuccessfulImMarkResponse, ErrorImMarkResponse> {
        val response = SlackRequestBuilder<SlackImMarkResponse>(authToken)
                .with(this.params)
                .toMethod("im.mark")
                .returnAsType(SlackImMarkResponse::class.java)
                .postWithJsonBody()

        return when {
            response.body is SuccessfulImMarkResponse -> {
                val responseEntity = response.body as SuccessfulImMarkResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            response.body is ErrorImMarkResponse -> {
                val responseEntity = response.body as ErrorImMarkResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
            else -> {
                throw UnknownResponseException(this::class, response)
            }
        }
    }
}