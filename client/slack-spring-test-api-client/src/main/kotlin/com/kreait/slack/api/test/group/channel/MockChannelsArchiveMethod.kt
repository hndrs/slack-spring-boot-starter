package com.kreait.slack.api.test.group.channel

import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelArchiveResponse
import com.kreait.slack.api.contract.jackson.group.channels.SlackChannelsArchiveRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelArchiveResponse
import com.kreait.slack.api.group.ApiCallResult
import com.kreait.slack.api.group.channels.ChannelsArchiveMethod
import com.kreait.slack.api.test.MockMethod

class MockChannelsArchiveMethod : ChannelsArchiveMethod(), MockMethod<SuccessfulChannelArchiveResponse, ErrorChannelArchiveResponse, SlackChannelsArchiveRequest> {

    override fun params(): SlackChannelsArchiveRequest {
        return params
    }

    override var successResponse: SuccessfulChannelArchiveResponse? = null
    override var failureResponse: ErrorChannelArchiveResponse? = null

    override fun request(): ApiCallResult<SuccessfulChannelArchiveResponse, ErrorChannelArchiveResponse> {

        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }
        return ApiCallResult(this.successResponse, this.failureResponse)
    }

}
