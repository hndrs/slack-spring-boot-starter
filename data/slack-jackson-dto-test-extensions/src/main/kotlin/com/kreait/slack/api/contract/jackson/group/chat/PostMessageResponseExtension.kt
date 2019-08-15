package com.kreait.slack.api.contract.jackson.group.chat

import com.kreait.slack.api.contract.jackson.common.InstantSample
import java.time.Instant

fun PostMessageRequest.Companion.sample(): PostMessageRequest {
    return PostMessageRequest(channel = "channelId", text = "text")
}

fun SuccessfulPostMessageResponse.Companion.sample(): SuccessfulPostMessageResponse = SuccessfulPostMessageResponse(true, "", InstantSample.sample())

fun ErrorPostMessageResponse.Companion.sample(): ErrorPostMessageResponse = ErrorPostMessageResponse(false, "")
