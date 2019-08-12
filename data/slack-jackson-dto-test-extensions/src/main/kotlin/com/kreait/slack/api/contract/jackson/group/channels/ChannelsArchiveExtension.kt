package com.kreait.slack.api.contract.jackson.group.channels

import com.kreait.slack.api.contract.jackson.group.channels.ErrorChannelArchiveResponse
import com.kreait.slack.api.contract.jackson.group.channels.SlackChannelsArchiveRequest
import com.kreait.slack.api.contract.jackson.group.channels.SuccessfulChannelArchiveResponse

fun SlackChannelsArchiveRequest.Companion.sample(): SlackChannelsArchiveRequest = SlackChannelsArchiveRequest("")

fun ErrorChannelArchiveResponse.Companion.sample(): ErrorChannelArchiveResponse = ErrorChannelArchiveResponse(false, "")

fun SuccessfulChannelArchiveResponse.Companion.sample(): SuccessfulChannelArchiveResponse = SuccessfulChannelArchiveResponse(true)
