package io.olaph.slack.client.spring.group.im

import io.olaph.slack.client.UnknownResponseException
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.im.ImCloseMethod
import io.olaph.slack.client.spring.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.im.ErrorImCloseResponse
import io.olaph.slack.dto.jackson.group.im.SlackImCloseResponse
import io.olaph.slack.dto.jackson.group.im.SuccessfulImCloseResponse

/**
 * https://api.slack.com/methods/im.close
 */
@Suppress("UNCHECKED_CAST")
class DefaultImCloseMethod(private val authToken: String) : ImCloseMethod() {
    override fun request(): ApiCallResult<SuccessfulImCloseResponse, ErrorImCloseResponse> {

        val response = SlackRequestBuilder<SlackImCloseResponse>(authToken)
                .with(this.params)
                .toMethod("im.close")
                .returnAsType(SlackImCloseResponse::class.java)
                .postWithJsonBody()

        return when {
            response.body is SuccessfulImCloseResponse -> {
                val responseEntity = response.body as SuccessfulImCloseResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }

            response.body is ErrorImCloseResponse -> {
                val responseEntity = response.body as ErrorImCloseResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
            else -> {
                throw UnknownResponseException(this::class, response)
            }
        }
    }
}
