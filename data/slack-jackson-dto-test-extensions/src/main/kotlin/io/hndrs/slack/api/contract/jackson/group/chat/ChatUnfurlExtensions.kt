package io.hndrs.slack.api.contract.jackson.group.chat

import io.hndrs.slack.api.contract.jackson.common.InstantSample

fun ChatUnfurlRequest.Companion.sample(): ChatUnfurlRequest {
    return ChatUnfurlRequest("", InstantSample.sample(), mapOf("url" to "unfurl"))
}

fun SuccessfulChatUnfurlResponse.Companion.sample(): SuccessfulChatUnfurlResponse {
    return SuccessfulChatUnfurlResponse(true)
}

fun ErrorChatUnfurlResponse.Companion.sample(): ErrorChatUnfurlResponse {
    return ErrorChatUnfurlResponse(false, "")
}
