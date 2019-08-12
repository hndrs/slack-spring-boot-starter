package com.kreait.slack.api.contract.jackson.group.chat

fun PostMessageRequest.Companion.sample(): PostMessageRequest {
    return PostMessageRequest(channel = "channelId", text = "text")
}

fun SuccessfulPostMessageResponse.Companion.sample(): SuccessfulPostMessageResponse = SuccessfulPostMessageResponse(true, "", "")

fun ErrorPostMessageResponse.Companion.sample(): ErrorPostMessageResponse = ErrorPostMessageResponse(false, "")
