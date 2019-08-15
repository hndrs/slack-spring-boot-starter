package com.kreait.slack.api.contract.jackson.group.channels

fun ChannelsKickRequest.Companion.sample(): ChannelsKickRequest = ChannelsKickRequest("", "")

fun SuccessfulChannelKickResponse.Companion.sample(): SuccessfulChannelKickResponse = SuccessfulChannelKickResponse(true)

fun ErrorChannelKickResponse.Companion.sample(): ErrorChannelKickResponse = ErrorChannelKickResponse(false, "")
