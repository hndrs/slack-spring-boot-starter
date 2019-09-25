package com.kreait.slack.api.test.group.channel

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsInfoRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelInfoResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelInfoResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsInfoMethod
import com.kreait.slack.api.group.channels.ChannelsMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [ChannelsMethodGroup.info]
 */
class MockChannelsInfoMethod : ChannelsInfoMethod(), MockMethod<SuccessfulChannelInfoResponse, ErrorChannelInfoResponse, ChannelsInfoRequest> {

    override fun params(): ChannelsInfoRequest = params

    override var successResponse: SuccessfulChannelInfoResponse? = null
    override var failureResponse: ErrorChannelInfoResponse? = null

    override fun request(): ApiCallResult<SuccessfulChannelInfoResponse, ErrorChannelInfoResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
