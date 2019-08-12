package com.kreait.slack.api.test.group.channel

import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelCreateResponse
import com.kreait.slack.api.contract.jackson.group.channels.SlackChannelCreateRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelCreateResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsCreateMethod
import com.kreait.slack.api.test.MockMethod

class MockChannelsCreateMethod : ChannelsCreateMethod(), MockMethod<SuccessfulChannelCreateResponse, ErrorChannelCreateResponse, SlackChannelCreateRequest> {

    override fun params(): SlackChannelCreateRequest {
        return params
    }

    override var successResponse: SuccessfulChannelCreateResponse? = null
    override var failureResponse: ErrorChannelCreateResponse? = null

    override fun request(): ApiCallResult<SuccessfulChannelCreateResponse, ErrorChannelCreateResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }
        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
