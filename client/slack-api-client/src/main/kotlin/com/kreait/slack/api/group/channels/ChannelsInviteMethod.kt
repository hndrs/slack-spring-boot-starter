package com.kreait.slack.api.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ChannelInviteRequest
import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelInviteResponse
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelInviteResponse
import com.kreait.slack.api.group.ApiCallMethod

/**
 * Abstract representation of an slack api operation
 * Invites a user to a channel.
 * https://api.slack.com/methods/channels.invite
 */
abstract class ChannelsInviteMethod : ApiCallMethod<ChannelsInviteMethod, SuccessfulChannelInviteResponse, ErrorChannelInviteResponse, ChannelInviteRequest>()
