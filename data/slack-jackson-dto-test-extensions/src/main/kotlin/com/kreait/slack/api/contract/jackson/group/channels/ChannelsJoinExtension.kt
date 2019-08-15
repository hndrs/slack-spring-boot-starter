package com.kreait.slack.api.contract.jackson.group.channels

import com.kreait.slack.api.contract.jackson.common.types.Channel
import com.kreait.slack.api.contract.jackson.common.types.sample

fun ChannelsJoinRequest.Companion.sample(): ChannelsJoinRequest = ChannelsJoinRequest("")

fun SuccessfulChannelsJoinResponse.Companion.sample(): SuccessfulChannelsJoinResponse = SuccessfulChannelsJoinResponse(true, Channel.sample())

fun ErrorChannelsJoinResponse.Companion.sample(): ErrorChannelsJoinResponse = ErrorChannelsJoinResponse(false, "")
