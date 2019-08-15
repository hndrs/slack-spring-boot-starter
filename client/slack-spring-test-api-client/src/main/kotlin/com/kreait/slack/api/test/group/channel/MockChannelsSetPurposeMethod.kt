package com.kreait.slack.api.test.group.channel

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsSetPurposeRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsSetPurposeResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsSetPurposeMethod
import com.kreait.slack.api.test.MockMethod

class MockChannelsSetPurposeMethod : ChannelsSetPurposeMethod(), MockMethod<SuccessfulChannelsSetPurposeResponse, ErrorChannelsSetPurposeResponse, ChannelsSetPurposeRequest> {

    override fun params(): ChannelsSetPurposeRequest {
        return params
    }

    override var successResponse: SuccessfulChannelsSetPurposeResponse? = null
    override var failureResponse: ErrorChannelsSetPurposeResponse? = null

    override fun request(): ApiCallResult<SuccessfulChannelsSetPurposeResponse, ErrorChannelsSetPurposeResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }
        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
