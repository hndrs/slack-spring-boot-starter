package com.kreait.slack.api.contract.jackson.group.channels

import com.kreait.slack.api.contract.jackson.common.types.Channel
import com.kreait.slack.api.contract.jackson.common.types.sample

fun ChannelRenameRequest.Companion.sample(): ChannelRenameRequest = ChannelRenameRequest("", "", true)

fun SuccessfulChannelRenameResponse.Companion.sample(): SuccessfulChannelRenameResponse =
    SuccessfulChannelRenameResponse(true, Channel.sample())

fun ErrorChannelRenameResponse.Companion.sample(): ErrorChannelRenameResponse = ErrorChannelRenameResponse(false, "")
