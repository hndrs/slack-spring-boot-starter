package com.kreait.slack.api.test.group.channel

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsLeaveRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsLeaveResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsLeaveResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsLeaveMethod
import com.kreait.slack.api.test.MockMethod

class MockChannelsLeaveMethod : ChannelsLeaveMethod(), MockMethod<SuccessfulChannelsLeaveResponse, ErrorChannelsLeaveResponse, ChannelsLeaveRequest> {

    override fun params(): ChannelsLeaveRequest {
        return params
    }

    override var successResponse: SuccessfulChannelsLeaveResponse? = null
    override var failureResponse: ErrorChannelsLeaveResponse? = null

    override fun request(): ApiCallResult<SuccessfulChannelsLeaveResponse, ErrorChannelsLeaveResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }
        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
