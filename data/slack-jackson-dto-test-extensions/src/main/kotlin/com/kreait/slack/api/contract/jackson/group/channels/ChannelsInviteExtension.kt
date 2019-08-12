package com.kreait.slack.api.contract.jackson.group.channels

import com.kreait.slack.api.contract.jackson.common.types.Channel

fun SlackChannelInviteRequest.Companion.sample(): SlackChannelInviteRequest = SlackChannelInviteRequest("", "")

fun SuccessfulChannelInviteResponse.Companion.sample(): SuccessfulChannelInviteResponse = SuccessfulChannelInviteResponse(true, Channel.sample())

fun ErrorChannelInviteResponse.Companion.sample(): ErrorChannelInviteResponse = ErrorChannelInviteResponse(false, "")
