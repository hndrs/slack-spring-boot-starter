package io.olaph.slack.dto.jackson.group.channels

fun SlackChannelCreateRequest.Companion.sample(): SlackChannelCreateRequest = SlackChannelCreateRequest("", true)

fun SuccessfulChannelCreateResponse.Companion.sample(): SuccessfulChannelCreateResponse = SuccessfulChannelCreateResponse(true, Channel.sample())

fun ErrorChannelCreateResponse.Companion.sample(): ErrorChannelCreateResponse = ErrorChannelCreateResponse(false, "", "")
