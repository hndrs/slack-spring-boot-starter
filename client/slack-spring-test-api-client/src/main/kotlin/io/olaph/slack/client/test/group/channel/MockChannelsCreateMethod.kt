package io.olaph.slack.client.test.group.channel

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.channels.ChannelsCreateMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.channels.ErrorChannelCreateResponse
import io.olaph.slack.dto.jackson.group.channels.SlackChannelCreateRequest
import io.olaph.slack.dto.jackson.group.channels.SuccessfulChannelCreateResponse

class MockChannelsCreateMethod : ChannelsCreateMethod(), MockMethod<SuccessfulChannelCreateResponse, ErrorChannelCreateResponse, SlackChannelCreateRequest> {

    override fun params(): SlackChannelCreateRequest {
        return params
    }

    override var successResponse: SuccessfulChannelCreateResponse? = null
    override var failureResponse: ErrorChannelCreateResponse? = null

    override fun request(): ApiCallResult<SuccessfulChannelCreateResponse, ErrorChannelCreateResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }
        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}