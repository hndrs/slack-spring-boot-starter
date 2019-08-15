package com.kreait.slack.api.test.group.channel

import com.kreait.slack.api.contract.jackson.group.channels.ChannelRepliesRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelRepliesResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelRepliesResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsRepliesMethod
import com.kreait.slack.api.test.MockMethod

class MockChannelsRepliesMethod : ChannelsRepliesMethod(), MockMethod<SuccessfulChannelRepliesResponse, ErrorChannelRepliesResponse, ChannelRepliesRequest> {

    override fun params(): ChannelRepliesRequest {
        return params
    }

    override var successResponse: SuccessfulChannelRepliesResponse? = null
    override var failureResponse: ErrorChannelRepliesResponse? = null

    override fun request(): ApiCallResult<SuccessfulChannelRepliesResponse, ErrorChannelRepliesResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }
        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
