package com.kreait.slack.api.group.channels

import com.kreait.slack.api.group.ApiCallMethod
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelInviteResponse
import com.kreait.slack.api.contract.jackson.group.channels.SlackChannelInviteRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelInviteResponse

abstract class ChannelsInviteMethod : ApiCallMethod<ChannelsInviteMethod, SuccessfulChannelInviteResponse, ErrorChannelInviteResponse, SlackChannelInviteRequest>()
