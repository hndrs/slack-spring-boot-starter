package com.kreait.slack.api.test.group.channel

import com.kreait.slack.api.contract.jackson.group.channels.ChannelSetPurposeRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelSetPurposeResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsSetPurposeMethod
import com.kreait.slack.api.test.MockMethod

class MockChannelsSetPurposeMethod : ChannelsSetPurposeMethod(), MockMethod<SuccessfulChannelSetPurposeResponse, ErrorChannelSetPurposeResponse, ChannelSetPurposeRequest> {

    override fun params(): ChannelSetPurposeRequest {
        return params
    }

    override var successResponse: SuccessfulChannelSetPurposeResponse? = null
    override var failureResponse: ErrorChannelSetPurposeResponse? = null

    override fun request(): ApiCallResult<SuccessfulChannelSetPurposeResponse, ErrorChannelSetPurposeResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }
        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
