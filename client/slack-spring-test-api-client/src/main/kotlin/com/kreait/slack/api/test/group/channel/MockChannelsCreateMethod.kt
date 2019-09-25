package com.kreait.slack.api.test.group.channel

import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsCreateResponse
import com.kreait.slack.api.contract.jackson.group.channels.ChannelsCreateRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsCreateResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsCreateMethod
import com.kreait.slack.api.test.MockMethod

/**
 * Mock implementation of [ChannelsCreateMethod]
 */
class MockChannelsCreateMethod : ChannelsCreateMethod(), MockMethod<SuccessfulChannelsCreateResponse, ErrorChannelsCreateResponse, ChannelsCreateRequest> {

    override fun params(): ChannelsCreateRequest = params

    override var successResponse: SuccessfulChannelsCreateResponse? = null
    override var failureResponse: ErrorChannelsCreateResponse? = null

    override fun request(): ApiCallResult<SuccessfulChannelsCreateResponse, ErrorChannelsCreateResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }
        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
