package com.kreait.slack.api.contract.jackson.group.chat

import java.time.Instant

fun ErrorChatUpdateResponse.Companion.sample(): ErrorChatUpdateResponse {
    return ErrorChatUpdateResponse(false, "")
}

fun SuccessfulChatUpdateResponse.Companion.sample(): SuccessfulChatUpdateResponse {
    return SuccessfulChatUpdateResponse(true, "", Instant.now(), "")
}

fun ChatUpdateRequest.Companion.sample(): ChatUpdateRequest {
    return ChatUpdateRequest("", "", Instant.now(), false, listOf(), false)
}
