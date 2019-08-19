package com.kreait.slack.api.contract.jackson.group.chat

import com.kreait.slack.api.contract.jackson.common.InstantSample

fun PostEphemeralRequest.Companion.sample(): PostEphemeralRequest {
    return PostEphemeralRequest(channel = "channelId")
}

fun SuccessfulPostEphemeralResponse.Companion.sample(): SuccessfulPostEphemeralResponse = SuccessfulPostEphemeralResponse(true, InstantSample.sample())

fun ErrorPostEphemeralResponse.Companion.sample(): ErrorPostEphemeralResponse = ErrorPostEphemeralResponse(false, "")
