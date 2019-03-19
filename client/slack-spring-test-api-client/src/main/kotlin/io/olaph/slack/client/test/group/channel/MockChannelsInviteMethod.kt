package io.olaph.slack.client.test.group.channel

import io.olaph.slack.client.group.ApiCallResult
import io.olaph.slack.client.group.channels.ChannelsInviteMethod
import io.olaph.slack.client.test.MockMethod
import io.olaph.slack.dto.jackson.group.channels.ErrorChannelInviteResponse
import io.olaph.slack.dto.jackson.group.channels.SlackChannelInviteRequest
import io.olaph.slack.dto.jackson.group.channels.SuccessfulChannelInviteResponse

class MockChannelsInviteMethod : ChannelsInviteMethod(), MockMethod<SuccessfulChannelInviteResponse, ErrorChannelInviteResponse, SlackChannelInviteRequest> {

    override fun params(): SlackChannelInviteRequest {
        return params
    }

    override var successResponse: SuccessfulChannelInviteResponse? = null
    override var failureResponse: ErrorChannelInviteResponse? = null

    override fun request(): ApiCallResult<SuccessfulChannelInviteResponse, ErrorChannelInviteResponse> {
        this.successResponse?.let { this.onSuccess?.invoke(it) }
        this.failureResponse?.let { this.onFailure?.invoke(it) }

        return ApiCallResult(this.successResponse, this.failureResponse)
    }
}
