package com.kreait.slack.api.contract.jackson.group.channels

import com.kreait.slack.api.contract.jackson.common.InstantSample
import java.time.Instant

fun ChannelsRepliesRequest.Companion.sample(): ChannelsRepliesRequest = ChannelsRepliesRequest("", Instant.ofEpochSecond(10000))

fun SuccessfulChannelsRepliesResponse.Companion.sample(): SuccessfulChannelsRepliesResponse = SuccessfulChannelsRepliesResponse(true, listOf(SuccessfulChannelsRepliesResponse.Message.sample()), false)

fun SuccessfulChannelsRepliesResponse.Message.Companion.sample() = SuccessfulChannelsRepliesResponse.Message(InstantSample.sample(), "",
        listOf(SuccessfulChannelsRepliesResponse.Reply.sample()), 0, false, "", InstantSample.sample(), InstantSample.sample(), "", 0, "")

fun SuccessfulChannelsRepliesResponse.Reply.Companion.sample() = SuccessfulChannelsRepliesResponse.Reply(InstantSample.sample(), "")

fun ErrorChannelsRepliesResponse.Companion.sample(): ErrorChannelsRepliesResponse = ErrorChannelsRepliesResponse(false, "", "")
