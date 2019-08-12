package com.kreait.slack.api.test.group.channel

import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelInfoResponse
import com.kreait.slack.api.contract.jackson.group.channels.ChannelsInfoRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelInfoResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsInfoMethod
import com.kreait.slack.api.test.MockMethod

class MockChannelsInfoMethod : ChannelsInfoMethod(), MockMethod<SuccessfulChannelInfoResponse, ErrorChannelInfoResponse, ChannelsInfoRequest> {

    override var successResponse: SuccessfulChannelInfoResponse? = null
    override var failureResponse: ErrorChannelInfoResponse? = null

    override fun params(): ChannelsInfoRequest {
        return params
    }

    override fun request(): ApiCallResult<SuccessfulChannelInfoResponse, ErrorChannelInfoResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
