package io.olaph.slack.client.test.group.channel

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.channels.ChannelsArchiveMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.channels.ErrorChannelArchiveResponse
import io.olaph.slack.dto.jackson.group.channels.SlackChannelsArchiveRequest
import io.olaph.slack.dto.jackson.group.channels.SuccessfulChannelArchiveResponse

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
