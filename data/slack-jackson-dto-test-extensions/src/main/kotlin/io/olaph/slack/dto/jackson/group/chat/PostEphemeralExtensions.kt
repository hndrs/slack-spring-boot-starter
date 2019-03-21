package io.olaph.slack.dto.jackson.group.chat

fun SlackPostEphemeralRequest.Companion.sample(): SlackPostEphemeralRequest {
    return SlackPostEphemeralRequest(channel = "channelId")
}

fun SuccessfulPostEphemeralResponse.Companion.sample(): SuccessfulPostEphemeralResponse = SuccessfulPostEphemeralResponse(true, "")

fun ErrorPostEphemeralResponse.Companion.sample(): ErrorPostEphemeralResponse = ErrorPostEphemeralResponse(false, "")
