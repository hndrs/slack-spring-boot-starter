package com.kreait.slack.api.contract.jackson.group.channels

import java.time.Instant

fun ChannelMarkRequest.Companion.sample(): ChannelMarkRequest = ChannelMarkRequest("", Instant.ofEpochSecond(10000))

fun SuccessfulChannelMarkResponse.Companion.sample(): SuccessfulChannelMarkResponse = SuccessfulChannelMarkResponse(true)

fun ErrorChannelMarkResponse.Companion.sample(): ErrorChannelMarkResponse = ErrorChannelMarkResponse(false, "", "")
