package com.kreait.slack.api.contract.jackson.group.chat

import java.time.Instant

fun ErrorChatUpdateResponse.Companion.sample(): ErrorChatUpdateResponse {
    return ErrorChatUpdateResponse(false, "")
}

fun SuccessfulChatUpdateResponse.Companion.sample(): SuccessfulChatUpdateResponse {
    return SuccessfulChatUpdateResponse(true, "", Instant.ofEpochSecond(10000), "")
}

fun ChatUpdateRequest.Companion.sample(): ChatUpdateRequest {
    return ChatUpdateRequest("", "", Instant.ofEpochSecond(10000), false, listOf(), false)
}
