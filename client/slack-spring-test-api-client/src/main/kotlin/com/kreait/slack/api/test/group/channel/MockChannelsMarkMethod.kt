package com.kreait.slack.api.test.group.channel

import com.kreait.slack.api.contract.jackson.group.channels.ChannelMarkRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelMarkResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelMarkResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsMarkMethod
import com.kreait.slack.api.test.MockMethod

class MockChannelsMarkMethod : ChannelsMarkMethod(), MockMethod<SuccessfulChannelMarkResponse, ErrorChannelMarkResponse, ChannelMarkRequest> {

    override fun params(): ChannelMarkRequest {
        return params
    }

    override var successResponse: SuccessfulChannelMarkResponse? = null
    override var failureResponse: ErrorChannelMarkResponse? = null

    override fun request(): ApiCallResult<SuccessfulChannelMarkResponse, ErrorChannelMarkResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }
        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
