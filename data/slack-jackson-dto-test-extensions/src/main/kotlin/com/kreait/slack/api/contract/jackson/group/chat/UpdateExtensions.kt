package com.kreait.slack.api.contract.jackson.group.chat

fun ErrorChatUpdateResponse.Companion.sample(): ErrorChatUpdateResponse {
    return ErrorChatUpdateResponse(false, "")
}

fun SuccessfulChatUpdateResponse.Companion.sample(): SuccessfulChatUpdateResponse {
    return SuccessfulChatUpdateResponse(true, "", "", "")
}

fun SlackChatUpdateRequest.Companion.sample(): SlackChatUpdateRequest {
    return SlackChatUpdateRequest("", "", "", false, listOf(), false)
}
