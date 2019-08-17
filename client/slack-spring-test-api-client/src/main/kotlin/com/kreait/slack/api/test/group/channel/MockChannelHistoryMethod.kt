package com.kreait.slack.api.test.group.channel

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsHistoryRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsHistoryResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsHistoryResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsHistoryMethod
import com.kreait.slack.api.test.MockMethod

class MockChannelHistoryMethod : ChannelsHistoryMethod(), MockMethod<SuccessfulChannelsHistoryResponse, ErrorChannelsHistoryResponse, ChannelsHistoryRequest> {

    override var successResponse: SuccessfulChannelsHistoryResponse? = null
    override var failureResponse: ErrorChannelsHistoryResponse? = null

    override fun params(): ChannelsHistoryRequest {
        return params
    }

    override fun request(): ApiCallResult<SuccessfulChannelsHistoryResponse, ErrorChannelsHistoryResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
