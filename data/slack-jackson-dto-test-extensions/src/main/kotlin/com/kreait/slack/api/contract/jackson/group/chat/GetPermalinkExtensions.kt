package com.kreait.slack.api.contract.jackson.group.chat

import java.time.Instant

fun ErrorChatGetPermalinkResponse.Companion.sample(): ErrorChatGetPermalinkResponse {
    return ErrorChatGetPermalinkResponse(false, "")
}

fun SuccessfulChatGetPermalinkResponse.Companion.sample(): SuccessfulChatGetPermalinkResponse {
    return SuccessfulChatGetPermalinkResponse(true, "", "")
}

fun ChatGetPermalinkRequest.Companion.sample(): ChatGetPermalinkRequest {
    return ChatGetPermalinkRequest("", Instant.now())
}
