package com.kreait.slack.api.contract.jackson.group.channels

import com.kreait.slack.api.contract.jackson.common.types.Channel
import com.kreait.slack.api.contract.jackson.common.types.sample

fun ChannelsSetTopicRequest.Companion.sample(): ChannelsSetTopicRequest = ChannelsSetTopicRequest("", "")

fun SuccessfulChannelsSetTopicResponse.Companion.sample(): SuccessfulChannelsSetTopicResponse = SuccessfulChannelsSetTopicResponse(true, Channel.sample())

fun ErrorChannelsSetTopicResponse.Companion.sample(): ErrorChannelsSetTopicResponse = ErrorChannelsSetTopicResponse(false, "", "")
