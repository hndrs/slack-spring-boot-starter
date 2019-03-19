package io.olaph.slack.dto.jackson.group.channels

fun SlackChannelsArchiveRequest.Companion.sample(): SlackChannelsArchiveRequest = SlackChannelsArchiveRequest("")

fun ErrorChannelArchiveResponse.Companion.sample(): ErrorChannelArchiveResponse = ErrorChannelArchiveResponse(false, "")

fun SuccessfulChannelArchiveResponse.Companion.sample(): SuccessfulChannelArchiveResponse = SuccessfulChannelArchiveResponse(true)
