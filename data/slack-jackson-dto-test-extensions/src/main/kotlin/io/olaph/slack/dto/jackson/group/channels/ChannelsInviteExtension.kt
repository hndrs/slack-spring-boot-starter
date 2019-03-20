package io.olaph.slack.dto.jackson.group.channels

import io.olaph.slack.dto.jackson.common.types.Channel

fun SlackChannelInviteRequest.Companion.sample(): SlackChannelInviteRequest = SlackChannelInviteRequest("", "")

fun SuccessfulChannelInviteResponse.Companion.sample(): SuccessfulChannelInviteResponse = SuccessfulChannelInviteResponse(true, Channel.sample())

fun ErrorChannelInviteResponse.Companion.sample(): ErrorChannelInviteResponse = ErrorChannelInviteResponse(false, "")
