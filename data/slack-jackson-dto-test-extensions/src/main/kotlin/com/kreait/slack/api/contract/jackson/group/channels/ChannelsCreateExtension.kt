package com.kreait.slack.api.contract.jackson.group.channels

import com.kreait.slack.api.contract.jackson.common.types.Channel
import com.kreait.slack.api.contract.jackson.common.types.sample

fun ChannelsCreateRequest.Companion.sample(): ChannelsCreateRequest = ChannelsCreateRequest("", true)

fun SuccessfulChannelsCreateResponse.Companion.sample(): SuccessfulChannelsCreateResponse = SuccessfulChannelsCreateResponse(true, Channel.sample())

fun ErrorChannelsCreateResponse.Companion.sample(): ErrorChannelsCreateResponse = ErrorChannelsCreateResponse(false, "", "")
