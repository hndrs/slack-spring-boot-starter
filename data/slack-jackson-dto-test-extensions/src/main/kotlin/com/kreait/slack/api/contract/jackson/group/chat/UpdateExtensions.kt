package com.kreait.slack.api.contract.jackson.group.chat

fun ErrorChatUpdateResponse.Companion.sample(): ErrorChatUpdateResponse {
    return ErrorChatUpdateResponse(false, "")
}

fun SuccessfulChatUpdateResponse.Companion.sample(): SuccessfulChatUpdateResponse {
    return SuccessfulChatUpdateResponse(true, "", "", "")
}

fun ChatUpdateRequest.Companion.sample(): ChatUpdateRequest {
    return ChatUpdateRequest("", "", "", false, listOf(), false)
}
