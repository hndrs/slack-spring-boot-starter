package io.olaph.slack.dto.jackson.group.channels

fun SlackChannelsInfoRequest.Companion.sample(): SlackChannelsInfoRequest = SlackChannelsInfoRequest("")

fun SuccessfulGetChannelInfoResponse.Companion.sample(): SuccessfulGetChannelInfoResponse = SuccessfulGetChannelInfoResponse(true, Channel.sample())

fun ErrorGetChannelInfoResponse.Companion.sample(): ErrorGetChannelInfoResponse = ErrorGetChannelInfoResponse(false, "")