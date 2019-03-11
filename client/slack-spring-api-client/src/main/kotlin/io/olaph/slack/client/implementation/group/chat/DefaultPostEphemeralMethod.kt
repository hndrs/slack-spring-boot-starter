package io.olaph.slack.client.implementation.group.chat

import io.olaph.slack.client.UnknownResponseException
import io.olaph.slack.client.implementation.group.SlackRequestBuilder
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.chat.ChatPostEphemeralMethod
import io.olaph.slack.dto.jackson.group.chat.ErrorPostEphemeralMessageResponse
import io.olaph.slack.dto.jackson.group.chat.SlackPostEphemeralMessageResponse
import io.olaph.slack.dto.jackson.group.chat.SuccessfulPostEphemeralMessageResponse

/**
 * Posts a Ephemeral message to a channel which is only visible to a specific user
 * @param config the configuration of the Slackclient
 * @return the API Call Method containing the ResponseEntities
 */
@Suppress("UNCHECKED_CAST")
class DefaultPostEphemeralMethod(private val authToken: String) : ChatPostEphemeralMethod() {

    override fun request(): ApiCallResult<SuccessfulPostEphemeralMessageResponse, ErrorPostEphemeralMessageResponse> {
        val response = SlackRequestBuilder<SlackPostEphemeralMessageResponse>(authToken)
                .with(this.params)
                .toMethod("chat.postEphemeral")
                .returnAsType(SlackPostEphemeralMessageResponse::class.java)
                .postWithJsonBody()

        return when {
            response.body is SuccessfulPostEphemeralMessageResponse -> {
                val responseEntity = response.body as SuccessfulPostEphemeralMessageResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            response.body is ErrorPostEphemeralMessageResponse -> {
                val responseEntity = response.body as ErrorPostEphemeralMessageResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
            else -> {
                throw UnknownResponseException(this::class, response)
            }
        }
    }
}
