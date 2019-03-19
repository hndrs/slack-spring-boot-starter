package io.olaph.slack.dto.jackson.group.chat

fun SlackChatDeleteRequest.Companion.sample(): SlackChatDeleteRequest {
    return SlackChatDeleteRequest("", "", false)
}

fun SuccessfulChatDeleteResponse.Companion.sample(): SuccessfulChatDeleteResponse {
    return SuccessfulChatDeleteResponse(true, "", "")
}

fun ErrorChatDeleteResponse.Companion.sample(): ErrorChatDeleteResponse {
    return ErrorChatDeleteResponse(false, "")
}