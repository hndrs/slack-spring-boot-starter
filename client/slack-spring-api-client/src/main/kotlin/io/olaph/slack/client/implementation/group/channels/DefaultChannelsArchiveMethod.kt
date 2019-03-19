package io.olaph.slack.client.implementation.group.channels

import io.olaph.slack.client.UnknownResponseException
import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.channels.ChannelsArchiveMethod
import io.olaph.slack.client.implementation.group.SlackRequestBuilder
import io.olaph.slack.dto.jackson.group.channels.ErrorChannelArchiveResponse
import io.olaph.slack.dto.jackson.group.channels.SlackGetChannelArchiveResponse
import io.olaph.slack.dto.jackson.group.channels.SuccessfulChannelArchiveResponse

@Suppress("UNCHECKED_CAST")
class DefaultChannelsArchiveMethod(private val authToken: String) : ChannelsArchiveMethod() {
    override fun request(): ApiCallResult<SuccessfulChannelArchiveResponse, ErrorChannelArchiveResponse> {
        val response = SlackRequestBuilder<SlackGetChannelArchiveResponse>(authToken)
                .with(this.params)
                .toMethod("channels.archive")
                .returnAsType(SlackGetChannelArchiveResponse::class.java)
                .postWithJsonBody()

        return when {
            response.body is SuccessfulChannelArchiveResponse -> {
                val responseEntity = response.body as SuccessfulChannelArchiveResponse
                this.onSuccess?.invoke(responseEntity)
                ApiCallResult(success = responseEntity)
            }
            response.body is ErrorChannelArchiveResponse -> {
                val responseEntity = response.body as ErrorChannelArchiveResponse
                this.onFailure?.invoke(responseEntity)
                ApiCallResult(failure = responseEntity)
            }
            else -> {
                throw UnknownResponseException(this::class, response)
            }
        }
    }
}
