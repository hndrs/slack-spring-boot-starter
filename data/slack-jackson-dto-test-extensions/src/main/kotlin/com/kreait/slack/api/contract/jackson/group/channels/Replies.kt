package com.kreait.slack.api.contract.jackson.group.channels

import com.kreait.slack.api.contract.jackson.common.types.Message
import com.kreait.slack.api.contract.jackson.group.chat.sample
import java.time.Instant

fun ChannelsRepliesRequest.Companion.sample(): ChannelsRepliesRequest = ChannelsRepliesRequest("", Instant.ofEpochSecond(10000))

fun SuccessfulChannelsRepliesResponse.Companion.sample(): SuccessfulChannelsRepliesResponse = SuccessfulChannelsRepliesResponse(true, listOf(Message.sample()), false)

fun ErrorChannelsRepliesResponse.Companion.sample(): ErrorChannelsRepliesResponse = ErrorChannelsRepliesResponse(false, "", "")
