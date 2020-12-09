package com.kreait.slack.api.contract.jackson.group.channels

import java.time.Instant

fun ChannelsMarkRequest.Companion.sample(): ChannelsMarkRequest = ChannelsMarkRequest("", Instant.ofEpochSecond(10000))

fun SuccessfulChannelsMarkResponse.Companion.sample(): SuccessfulChannelsMarkResponse =
    SuccessfulChannelsMarkResponse(true)

fun ErrorChannelsMarkResponse.Companion.sample(): ErrorChannelsMarkResponse = ErrorChannelsMarkResponse(false, "", "")
