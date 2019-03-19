package io.olaph.slack.client.test.group.channel

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.channels.ChannelsInfoMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.channels.ErrorGetChannelInfoResponse
import io.olaph.slack.dto.jackson.group.channels.SlackChannelsInfoRequest
import io.olaph.slack.dto.jackson.group.channels.SuccessfulGetChannelInfoResponse

class MockChannelsInfoMethod : ChannelsInfoMethod(), MockMethod<SuccessfulGetChannelInfoResponse, ErrorGetChannelInfoResponse, SlackChannelsInfoRequest> {

    override var successResponse: SuccessfulGetChannelInfoResponse? = null
    override var failureResponse: ErrorGetChannelInfoResponse? = null

    override fun params(): SlackChannelsInfoRequest {
        return params
    }

    override fun request(): ApiCallResult<SuccessfulGetChannelInfoResponse, ErrorGetChannelInfoResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}