package com.kreait.slack.api.test.group.channel

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsJoinRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsJoinResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsJoinResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsJoinMethod
import com.kreait.slack.api.group.channels.ChannelsMethodGroup
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [ChannelsMethodGroup.join]
 */
class MockChannelsJoinMethod : ChannelsJoinMethod(), MockMethod<SuccessfulChannelsJoinResponse, ErrorChannelsJoinResponse, ChannelsJoinRequest> {

    override fun params(): ChannelsJoinRequest = params

    override var successResponse: SuccessfulChannelsJoinResponse? = null
    override var failureResponse: ErrorChannelsJoinResponse? = null

    override fun request(): ApiCallResult<SuccessfulChannelsJoinResponse, ErrorChannelsJoinResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
