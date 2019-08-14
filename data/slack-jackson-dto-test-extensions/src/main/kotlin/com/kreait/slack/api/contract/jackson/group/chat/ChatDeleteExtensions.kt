package com.kreait.slack.api.contract.jackson.group.chat

import java.time.Instant

fun ChatDeleteRequest.Companion.sample(): ChatDeleteRequest {
    return ChatDeleteRequest("", Instant.now(), false)
}

fun SuccessfulChatDeleteResponse.Companion.sample(): SuccessfulChatDeleteResponse {
    return SuccessfulChatDeleteResponse(true, "", Instant.now())
}

fun ErrorChatDeleteResponse.Companion.sample(): ErrorChatDeleteResponse {
    return ErrorChatDeleteResponse(false, "")
}
