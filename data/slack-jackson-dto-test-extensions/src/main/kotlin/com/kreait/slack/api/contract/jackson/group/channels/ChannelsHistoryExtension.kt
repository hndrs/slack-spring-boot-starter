package com.kreait.slack.api.contract.jackson.group.channels

import java.time.Instant

fun ChannelsHistoryRequest.Companion.sample(): ChannelsHistoryRequest = ChannelsHistoryRequest("")

fun SuccessfulChannelsHistoryResponse.Companion.sample(): SuccessfulChannelsHistoryResponse =
        SuccessfulChannelsHistoryResponse(true, listOf(SuccessfulChannelsHistoryResponse.Message.sample()),
                Instant.ofEpochSecond(10000), false)

fun ErrorChannelsHistoryResponse.Companion.sample(): ErrorChannelsHistoryResponse = ErrorChannelsHistoryResponse(false, "")

fun SuccessfulChannelsHistoryResponse.Message.Companion.sample(): SuccessfulChannelsHistoryResponse.Message =
        SuccessfulChannelsHistoryResponse.Message(listOf(SuccessfulChannelsHistoryResponse.Attachment.sample()), "", false,
                listOf(SuccessfulChannelsHistoryResponse.Reaction.sample()), "", "", Instant.ofEpochSecond(10000),
                "", "", "")

fun SuccessfulChannelsHistoryResponse.Reaction.Companion.sample(): SuccessfulChannelsHistoryResponse.Reaction =
        SuccessfulChannelsHistoryResponse.Reaction(1, "", listOf(""))

fun SuccessfulChannelsHistoryResponse.Attachment.Companion.sample(): SuccessfulChannelsHistoryResponse.Attachment =
        SuccessfulChannelsHistoryResponse.Attachment("", 0, "")
