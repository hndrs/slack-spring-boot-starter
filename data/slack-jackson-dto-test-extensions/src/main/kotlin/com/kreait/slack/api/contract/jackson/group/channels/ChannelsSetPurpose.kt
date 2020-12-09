package com.kreait.slack.api.contract.jackson.group.channels

fun ChannelsSetPurposeRequest.Companion.sample(): ChannelsSetPurposeRequest = ChannelsSetPurposeRequest("", "")

fun SuccessfulChannelsSetPurposeResponse.Companion.sample(): SuccessfulChannelsSetPurposeResponse =
    SuccessfulChannelsSetPurposeResponse(true, "")

fun ErrorChannelsSetPurposeResponse.Companion.sample(): ErrorChannelsSetPurposeResponse =
    ErrorChannelsSetPurposeResponse(false, "")
