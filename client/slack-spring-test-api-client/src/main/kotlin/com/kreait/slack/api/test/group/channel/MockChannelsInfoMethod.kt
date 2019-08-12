package com.kreait.slack.api.test.group.channel

import com.kreait.slack.api.contract.jackson.group.channels.ErrorGetChannelInfoResponse
import com.kreait.slack.api.contract.jackson.group.channels.SlackChannelsInfoRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulGetChannelInfoResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsInfoMethod
import com.kreait.slack.api.test.MockMethod

class MockChannelsInfoMethod : ChannelsInfoMethod(), MockMethod<SuccessfulGetChannelInfoResponse, ErrorGetChannelInfoResponse, SlackChannelsInfoRequest> {

    override var successResponse: SuccessfulGetChannelInfoResponse? = null
    override var failureResponse: ErrorGetChannelInfoResponse? = null

    override fun params(): SlackChannelsInfoRequest {
        return params
    }

    override fun request(): ApiCallResult<SuccessfulGetChannelInfoResponse, ErrorGetChannelInfoResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
