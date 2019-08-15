package com.kreait.slack.api.contract.jackson.group.chat

import java.time.Instant

fun ChatDeleteRequest.Companion.sample(): ChatDeleteRequest {
    return ChatDeleteRequest("", Instant.ofEpochSecond(10000), false)
}

fun SuccessfulChatDeleteResponse.Companion.sample(): SuccessfulChatDeleteResponse {
    return SuccessfulChatDeleteResponse(true, "", Instant.ofEpochSecond(10000))
}

fun ErrorChatDeleteResponse.Companion.sample(): ErrorChatDeleteResponse {
    return ErrorChatDeleteResponse(false, "")
}
