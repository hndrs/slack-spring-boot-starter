package com.kreait.slack.api.contract.jackson.group.chat

import java.time.Instant

fun PostMessageRequest.Companion.sample(): PostMessageRequest {
    return PostMessageRequest(channel = "channelId", text = "text")
}

fun SuccessfulPostMessageResponse.Companion.sample(): SuccessfulPostMessageResponse = SuccessfulPostMessageResponse(true, "", Instant.now())

fun ErrorPostMessageResponse.Companion.sample(): ErrorPostMessageResponse = ErrorPostMessageResponse(false, "")
