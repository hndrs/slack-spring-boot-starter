package com.kreait.slack.api.test.group.channel

import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelInviteResponse
import com.kreait.slack.api.contract.jackson.group.channels.ChannelInviteRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelInviteResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsInviteMethod
import com.kreait.slack.api.test.MockMethod

class MockChannelsInviteMethod : ChannelsInviteMethod(), MockMethod<SuccessfulChannelInviteResponse, ErrorChannelInviteResponse, ChannelInviteRequest> {

    override fun params(): ChannelInviteRequest {
        return params
    }

    override var successResponse: SuccessfulChannelInviteResponse? = null
    override var failureResponse: ErrorChannelInviteResponse? = null

    override fun request(): ApiCallResult<SuccessfulChannelInviteResponse, ErrorChannelInviteResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
