package com.kreait.slack.api.contract.jackson.group.channels

import com.kreait.slack.api.contract.jackson.common.types.Channel

fun SlackChannelsInfoRequest.Companion.sample(): SlackChannelsInfoRequest = SlackChannelsInfoRequest("")

fun SuccessfulGetChannelInfoResponse.Companion.sample(): SuccessfulGetChannelInfoResponse = SuccessfulGetChannelInfoResponse(true, Channel.sample())

fun ErrorGetChannelInfoResponse.Companion.sample(): ErrorGetChannelInfoResponse = ErrorGetChannelInfoResponse(false, "")
