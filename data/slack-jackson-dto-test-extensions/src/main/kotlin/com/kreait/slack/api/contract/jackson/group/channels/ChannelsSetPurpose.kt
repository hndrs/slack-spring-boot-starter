package com.kreait.slack.api.contract.jackson.group.channels

fun ChannelSetPurposeRequest.Companion.sample(): ChannelSetPurposeRequest = ChannelSetPurposeRequest("", "")

fun SuccessfulChannelSetPurposeResponse.Companion.sample(): SuccessfulChannelSetPurposeResponse = SuccessfulChannelSetPurposeResponse(true, "")

fun ErrorChannelSetPurposeResponse.Companion.sample(): ErrorChannelSetPurposeResponse = ErrorChannelSetPurposeResponse(false, "")
