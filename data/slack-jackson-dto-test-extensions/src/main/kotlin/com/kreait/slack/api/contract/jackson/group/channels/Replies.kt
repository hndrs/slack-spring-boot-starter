package com.kreait.slack.api.contract.jackson.group.channels

import java.time.Instant

fun ChannelRepliesRequest.Companion.sample(): ChannelRepliesRequest = ChannelRepliesRequest("", Instant.ofEpochSecond(10000))

fun SuccessfulChannelRepliesResponse.Companion.sample(): SuccessfulChannelRepliesResponse = SuccessfulChannelRepliesResponse(true, listOf(SuccessfulChannelRepliesResponse.Message.sample()), false)

fun SuccessfulChannelRepliesResponse.Message.Companion.sample() = SuccessfulChannelRepliesResponse.Message("", "",
        listOf(SuccessfulChannelRepliesResponse.Reply.sample()), 0, false, "", "", "", "", 0, "")

fun SuccessfulChannelRepliesResponse.Reply.Companion.sample() = SuccessfulChannelRepliesResponse.Reply("", "")

fun ErrorChannelRepliesResponse.Companion.sample(): ErrorChannelRepliesResponse = ErrorChannelRepliesResponse(false, "", "")
