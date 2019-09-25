package com.kreait.slack.api.test.group.channel

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsMarkRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsMarkResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsMarkResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsMarkMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Mock implementation of [ChannelsMarkMethod]
 */
class MockChannelsMarkMethod : ChannelsMarkMethod(), MockMethod<SuccessfulChannelsMarkResponse, ErrorChannelsMarkResponse, ChannelsMarkRequest> {

    override fun params(): ChannelsMarkRequest = params

    override var successResponse: SuccessfulChannelsMarkResponse? = null
    override var failureResponse: ErrorChannelsMarkResponse? = null

    override fun request(): ApiCallResult<SuccessfulChannelsMarkResponse, ErrorChannelsMarkResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }
        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
