package com.kreait.slack.api.contract.jackson.group.chat

fun PostEphemeralRequest.Companion.sample(): PostEphemeralRequest {
    return PostEphemeralRequest(channel = "channelId")
}

fun SuccessfulPostEphemeralResponse.Companion.sample(): SuccessfulPostEphemeralResponse = SuccessfulPostEphemeralResponse(true, "")

fun ErrorPostEphemeralResponse.Companion.sample(): ErrorPostEphemeralResponse = ErrorPostEphemeralResponse(false, "")
