package com.kreait.slack.api.contract.jackson.group.channels

import com.kreait.slack.api.contract.jackson.common.types.Channel
import com.kreait.slack.api.contract.jackson.common.types.sample

fun ChannelsInfoRequest.Companion.sample(): ChannelsInfoRequest = ChannelsInfoRequest("")

fun SuccessfulChannelInfoResponse.Companion.sample(): SuccessfulChannelInfoResponse = SuccessfulChannelInfoResponse(true, Channel.sample())

fun ErrorChannelInfoResponse.Companion.sample(): ErrorChannelInfoResponse = ErrorChannelInfoResponse(false, "")
