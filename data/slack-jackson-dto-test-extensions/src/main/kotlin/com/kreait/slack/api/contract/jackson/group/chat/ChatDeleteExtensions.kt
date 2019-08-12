package com.kreait.slack.api.contract.jackson.group.chat

import com.kreait.slack.api.contract.jackson.group.chat.ErrorChatDeleteResponse
import com.kreait.slack.api.contract.jackson.group.chat.SlackChatDeleteRequest
import com.kreait.slack.api.contract.jackson.group.chat.SuccessfulChatDeleteResponse

fun SlackChatDeleteRequest.Companion.sample(): SlackChatDeleteRequest {
    return SlackChatDeleteRequest("", "", false)
}

fun SuccessfulChatDeleteResponse.Companion.sample(): SuccessfulChatDeleteResponse {
    return SuccessfulChatDeleteResponse(true, "", "")
}

fun ErrorChatDeleteResponse.Companion.sample(): ErrorChatDeleteResponse {
    return ErrorChatDeleteResponse(false, "")
}
