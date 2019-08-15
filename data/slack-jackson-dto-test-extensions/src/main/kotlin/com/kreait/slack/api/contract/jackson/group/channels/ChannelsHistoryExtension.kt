package com.kreait.slack.api.contract.jackson.group.channels

import java.time.Instant

fun ChannelsHistoryRequest.Companion.sample(): ChannelsHistoryRequest = ChannelsHistoryRequest("")

fun SuccessfulChannelHistoryResponse.Companion.sample(): SuccessfulChannelHistoryResponse =
        SuccessfulChannelHistoryResponse(true, listOf(SuccessfulChannelHistoryResponse.Message.sample()),
                Instant.ofEpochSecond(10000), false)

fun ErrorChannelHistoryResponse.Companion.sample(): ErrorChannelHistoryResponse = ErrorChannelHistoryResponse(false, "")

fun SuccessfulChannelHistoryResponse.Message.Companion.sample(): SuccessfulChannelHistoryResponse.Message =
        SuccessfulChannelHistoryResponse.Message(listOf(SuccessfulChannelHistoryResponse.Attachment.sample()), "", false,
                listOf(SuccessfulChannelHistoryResponse.Reaction.sample()), "", "", Instant.ofEpochSecond(10000),
                "", "", "")

fun SuccessfulChannelHistoryResponse.Reaction.Companion.sample(): SuccessfulChannelHistoryResponse.Reaction =
        SuccessfulChannelHistoryResponse.Reaction(1, "", listOf(""))

fun SuccessfulChannelHistoryResponse.Attachment.Companion.sample(): SuccessfulChannelHistoryResponse.Attachment =
        SuccessfulChannelHistoryResponse.Attachment("", 0, "")