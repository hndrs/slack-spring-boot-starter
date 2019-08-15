package com.kreait.slack.api.test.group.channel

import com.kreait.slack.api.contract.jackson.group.channels.ChannelsSetTopicRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelsSetTopicResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelsSetTopicResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsSetTopicMethod
import com.kreait.slack.api.test.MockMethod

class MockChannelsSetTopicMethod : ChannelsSetTopicMethod(), MockMethod<SuccessfulChannelsSetTopicResponse, ErrorChannelsSetTopicResponse, ChannelsSetTopicRequest> {

    override fun params(): ChannelsSetTopicRequest {
        return params
    }

    override var successResponse: SuccessfulChannelsSetTopicResponse? = null
    override var failureResponse: ErrorChannelsSetTopicResponse? = null

    override fun request(): ApiCallResult<SuccessfulChannelsSetTopicResponse, ErrorChannelsSetTopicResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }
        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
