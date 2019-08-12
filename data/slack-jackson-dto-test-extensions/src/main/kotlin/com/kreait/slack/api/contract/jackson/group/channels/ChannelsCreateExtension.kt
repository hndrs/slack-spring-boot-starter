package com.kreait.slack.api.contract.jackson.group.channels

import com.kreait.slack.api.contract.jackson.common.types.Channel

fun SlackChannelCreateRequest.Companion.sample(): SlackChannelCreateRequest = SlackChannelCreateRequest("", true)

fun SuccessfulChannelCreateResponse.Companion.sample(): SuccessfulChannelCreateResponse = SuccessfulChannelCreateResponse(true, Channel.sample())

fun ErrorChannelCreateResponse.Companion.sample(): ErrorChannelCreateResponse = ErrorChannelCreateResponse(false, "", "")
