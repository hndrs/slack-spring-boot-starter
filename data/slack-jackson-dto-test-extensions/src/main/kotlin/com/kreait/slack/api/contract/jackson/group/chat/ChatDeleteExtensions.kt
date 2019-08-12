package com.kreait.slack.api.contract.jackson.group.chat

fun ChatDeleteRequest.Companion.sample(): ChatDeleteRequest {
    return ChatDeleteRequest("", "", false)
}

fun SuccessfulChatDeleteResponse.Companion.sample(): SuccessfulChatDeleteResponse {
    return SuccessfulChatDeleteResponse(true, "", "")
}

fun ErrorChatDeleteResponse.Companion.sample(): ErrorChatDeleteResponse {
    return ErrorChatDeleteResponse(false, "")
}
