package com.kreait.slack.api.contract.jackson.group.channels

fun ChannelsLeaveRequest.Companion.sample(): ChannelsLeaveRequest = ChannelsLeaveRequest("")

fun SuccessfulChannelsLeaveResponse.Companion.sample(): SuccessfulChannelsLeaveResponse = SuccessfulChannelsLeaveResponse(true)

fun ErrorChannelsLeaveResponse.Companion.sample(): ErrorChannelsLeaveResponse = ErrorChannelsLeaveResponse(false, "", "")
