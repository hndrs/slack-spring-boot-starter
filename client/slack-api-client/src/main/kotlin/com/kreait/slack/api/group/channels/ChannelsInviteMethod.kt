package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelInviteResponse
import com.kreait.slack.api.contract.jackson.group.channels.ChannelInviteRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelInviteResponse
import com.kreait.slack.api.group.ApiCallMethod

abstract class ChannelsInviteMethod : ApiCallMethod<ChannelsInviteMethod, SuccessfulChannelInviteResponse, ErrorChannelInviteResponse, ChannelInviteRequest>()
