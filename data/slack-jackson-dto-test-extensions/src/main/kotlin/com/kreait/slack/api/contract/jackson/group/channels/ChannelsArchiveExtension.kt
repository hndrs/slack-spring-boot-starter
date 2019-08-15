package com.kreait.slack.api.contract.jackson.group.channels

fun ChannelsUnarchiveRequest.Companion.sample(): ChannelsUnarchiveRequest = ChannelsUnarchiveRequest("")

fun ErrorChannelUnarchiveResponse.Companion.sample(): ErrorChannelUnarchiveResponse = ErrorChannelUnarchiveResponse(false, "")

fun SuccessfulChannelUnarchiveResponse.Companion.sample(): SuccessfulChannelUnarchiveResponse = SuccessfulChannelUnarchiveResponse(true)
