package com.kreait.slack.api.test.group.channel

import com.kreait.slack.api.contract.jackson.group.channels.ChannelRenameRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelRenameResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelRenameResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsRenameMethod
import com.kreait.slack.api.test.MockMethod

class MockChannelsRenameMethod : ChannelsRenameMethod(), MockMethod<SuccessfulChannelRenameResponse, ErrorChannelRenameResponse, ChannelRenameRequest> {

    override fun params(): ChannelRenameRequest {
        return params
    }

    override var successResponse: SuccessfulChannelRenameResponse? = null
    override var failureResponse: ErrorChannelRenameResponse? = null

    override fun request(): ApiCallResult<SuccessfulChannelRenameResponse, ErrorChannelRenameResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }
        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
