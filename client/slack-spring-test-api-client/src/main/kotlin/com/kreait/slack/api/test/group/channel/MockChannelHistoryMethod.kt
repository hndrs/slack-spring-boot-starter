package com.kreait.slack.api.test.group.channel

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsHistoryRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelHistoryResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelHistoryResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsHistoryMethod
import com.kreait.slack.api.test.MockMethod

class MockChannelHistoryMethod : ChannelsHistoryMethod(), MockMethod<SuccessfulChannelHistoryResponse, ErrorChannelHistoryResponse, ChannelsHistoryRequest> {

    override var successResponse: SuccessfulChannelHistoryResponse? = null
    override var failureResponse: ErrorChannelHistoryResponse? = null

    override fun params(): ChannelsHistoryRequest {
        return params
    }

    override fun request(): ApiCallResult<SuccessfulChannelHistoryResponse, ErrorChannelHistoryResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
