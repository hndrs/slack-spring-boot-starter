package com.kreait.slack.api.contract.jackson.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsInfoRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationsInfoResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationsInfoResponse
import com.kreait.slack.api.contract.jackson.common.types.Channel

fun SuccessfulConversationsInfoResponse.Companion.sample(): SuccessfulConversationsInfoResponse {
    return SuccessfulConversationsInfoResponse(true, Channel.sample())
}

fun ErrorConversationsInfoResponse.Companion.sample(): ErrorConversationsInfoResponse {
    return ErrorConversationsInfoResponse(false, "")
}


fun ConversationsInfoRequest.Companion.sample(): ConversationsInfoRequest {
    return ConversationsInfoRequest("")
}
