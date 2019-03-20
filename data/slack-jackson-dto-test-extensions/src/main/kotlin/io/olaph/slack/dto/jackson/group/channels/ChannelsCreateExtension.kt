package io.olaph.slack.dto.jackson.group.channels

import io.olaph.slack.dto.jackson.common.types.Channel

fun SlackChannelCreateRequest.Companion.sample(): SlackChannelCreateRequest = SlackChannelCreateRequest("", true)

fun SuccessfulChannelCreateResponse.Companion.sample(): SuccessfulChannelCreateResponse = SuccessfulChannelCreateResponse(true, Channel.sample())

fun ErrorChannelCreateResponse.Companion.sample(): ErrorChannelCreateResponse = ErrorChannelCreateResponse(false, "", "")
