package com.kreait.slack.api.test.group.channel

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsRepliesRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsRepliesResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsRepliesResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsRepliesMethod
import com.kreait.slack.api.test.MockMethod

class MockChannelsRepliesMethod : ChannelsRepliesMethod(), MockMethod<SuccessfulChannelsRepliesResponse, ErrorChannelsRepliesResponse, ChannelsRepliesRequest> {

    override fun params(): ChannelsRepliesRequest {
        return params
    }

    override var successResponse: SuccessfulChannelsRepliesResponse? = null
    override var failureResponse: ErrorChannelsRepliesResponse? = null

    override fun request(): ApiCallResult<SuccessfulChannelsRepliesResponse, ErrorChannelsRepliesResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }
        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
