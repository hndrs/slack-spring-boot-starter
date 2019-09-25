package com.kreait.slack.api.test.group.channel

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsUnarchiveRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelUnarchiveResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsMethodGroup
import com.kreait.slack.api.group.channels.ChannelsUnarchiveMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Testable implementation of [ChannelsMethodGroup.unarchive]
 */
class MockChannelsUnarchiveMethod : ChannelsUnarchiveMethod(), MockMethod<SuccessfulChannelUnarchiveResponse, ErrorChannelUnarchiveResponse, ChannelsUnarchiveRequest> {

    override fun params(): ChannelsUnarchiveRequest = params

    override var successResponse: SuccessfulChannelUnarchiveResponse? = null
    override var failureResponse: ErrorChannelUnarchiveResponse? = null

    override fun request(): ApiCallResult<SuccessfulChannelUnarchiveResponse, ErrorChannelUnarchiveResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
