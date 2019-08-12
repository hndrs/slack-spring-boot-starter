package com.kreait.slack.api.contract.jackson.group.channels

import com.kreait.slack.api.contract.jackson.common.types.Channel

fun ChannelInviteRequest.Companion.sample(): ChannelInviteRequest = ChannelInviteRequest("", "")

fun SuccessfulChannelInviteResponse.Companion.sample(): SuccessfulChannelInviteResponse = SuccessfulChannelInviteResponse(true, Channel.sample())

fun ErrorChannelInviteResponse.Companion.sample(): ErrorChannelInviteResponse = ErrorChannelInviteResponse(false, "")
