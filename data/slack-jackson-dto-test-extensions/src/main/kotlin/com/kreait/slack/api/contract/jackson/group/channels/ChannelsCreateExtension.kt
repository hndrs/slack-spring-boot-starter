package com.kreait.slack.api.contract.jackson.group.channels

import com.kreait.slack.api.contract.jackson.common.types.Channel

fun ChannelCreateRequest.Companion.sample(): ChannelCreateRequest = ChannelCreateRequest("", true)

fun SuccessfulChannelCreateResponse.Companion.sample(): SuccessfulChannelCreateResponse = SuccessfulChannelCreateResponse(true, Channel.sample())

fun ErrorChannelCreateResponse.Companion.sample(): ErrorChannelCreateResponse = ErrorChannelCreateResponse(false, "", "")
