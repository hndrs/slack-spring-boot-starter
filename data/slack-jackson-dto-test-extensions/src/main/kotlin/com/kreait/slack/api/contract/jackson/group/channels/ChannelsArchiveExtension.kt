package com.kreait.slack.api.contract.jackson.group.channels

fun ChannelsArchiveRequest.Companion.sample(): ChannelsArchiveRequest = ChannelsArchiveRequest("")

fun ErrorChannelArchiveResponse.Companion.sample(): ErrorChannelArchiveResponse = ErrorChannelArchiveResponse(false, "")

fun SuccessfulChannelArchiveResponse.Companion.sample(): SuccessfulChannelArchiveResponse = SuccessfulChannelArchiveResponse(true)
