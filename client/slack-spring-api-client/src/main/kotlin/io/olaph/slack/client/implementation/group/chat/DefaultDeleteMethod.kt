package io.olaph.slack.client.implementation.group.chat

import io.olaph.slack.client.UnknownResponseException
import io.olaph.slack.client.implementation.group.SlackRequestBuilder
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.chat.ChatDeleteMethod
import io.olaph.slack.dto.jackson.group.chat.ErrorDeleteResponse
import io.olaph.slack.dto.jackson.group.chat.SlackDeleteResponse
import io.olaph.slack.dto.jackson.group.chat.SuccessfulDeleteResponse

@Suppress("UNCHECKED_CAST")
class DefaultDeleteMethod(private val authToken: String) : ChatDeleteMethod() {

    override fun request(): ApiCallResult<SuccessfulDeleteResponse, ErrorDeleteResponse> {
        val response = SlackRequestBuilder<SlackDeleteResponse>(authToken)
                .with(this.params)
                .toMethod("chat.delete")
                .returnAsType(SlackDeleteResponse::class.java)
                .postWithJsonBody()

        return when {
            response.body is SuccessfulDeleteResponse -> {
                val responseEntity = response.body as SuccessfulDeleteResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            response.body is ErrorDeleteResponse -> {
                val responseEntity = response.body as ErrorDeleteResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
            else -> {
                throw UnknownResponseException(this::class, response)
            }
        }
    }


}
