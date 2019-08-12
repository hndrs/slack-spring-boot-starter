package com.kreait.slack.api.contract.jackson.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ErrorPostEphemeralResponse
import com.kreait.slack.api.contract.jackson.group.chat.SlackPostEphemeralRequest
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulPostEphemeralResponse

fun SlackPostEphemeralRequest.Companion.sample(): SlackPostEphemeralRequest {
    return SlackPostEphemeralRequest(channel = "channelId")
}

fun SuccessfulPostEphemeralResponse.Companion.sample(): SuccessfulPostEphemeralResponse = SuccessfulPostEphemeralResponse(true, "")

fun ErrorPostEphemeralResponse.Companion.sample(): ErrorPostEphemeralResponse = ErrorPostEphemeralResponse(false, "")
