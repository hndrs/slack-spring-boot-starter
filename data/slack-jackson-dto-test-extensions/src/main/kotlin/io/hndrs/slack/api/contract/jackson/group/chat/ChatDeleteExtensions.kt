package io.hndrs.slack.api.contract.jackson.group.chat

import io.hndrs.slack.api.contract.jackson.common.InstantSample

fun ChatDeleteRequest.Companion.sample(): ChatDeleteRequest {
    return ChatDeleteRequest("", InstantSample.sample(), false)
}

fun SuccessfulChatDeleteResponse.Companion.sample(): SuccessfulChatDeleteResponse {
    return SuccessfulChatDeleteResponse(true, "", InstantSample.sample())
}

fun ErrorChatDeleteResponse.Companion.sample(): ErrorChatDeleteResponse {
    return ErrorChatDeleteResponse(false, "")
}
