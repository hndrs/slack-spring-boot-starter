package com.kreait.slack.api.contract.jackson.group.channels

import com.kreait.slack.api.contract.jackson.common.types.Message
import com.kreait.slack.api.contract.jackson.group.chat.sample
import java.time.Instant

fun ChannelsHistoryRequest.Companion.sample(): ChannelsHistoryRequest = ChannelsHistoryRequest("")

fun SuccessfulChannelsHistoryResponse.Companion.sample(): SuccessfulChannelsHistoryResponse =
    SuccessfulChannelsHistoryResponse(
        true, listOf(Message.sample()),
        Instant.ofEpochSecond(10000), false
    )

fun ErrorChannelsHistoryResponse.Companion.sample(): ErrorChannelsHistoryResponse =
    ErrorChannelsHistoryResponse(false, "")
