package com.kreait.slack.api.test.group.channel

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsKickRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelKickResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelKickResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsKickMethod
import com.kreait.slack.api.group.channels.ChannelsMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [ChannelsMethodGroup.kick]
 */
class MockChannelsKickMethod : ChannelsKickMethod(), MockMethod<SuccessfulChannelKickResponse, ErrorChannelKickResponse, ChannelsKickRequest> {

    override fun params(): ChannelsKickRequest = params

    override var successResponse: SuccessfulChannelKickResponse? = null
    override var failureResponse: ErrorChannelKickResponse? = null

    override fun request(): ApiCallResult<SuccessfulChannelKickResponse, ErrorChannelKickResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
