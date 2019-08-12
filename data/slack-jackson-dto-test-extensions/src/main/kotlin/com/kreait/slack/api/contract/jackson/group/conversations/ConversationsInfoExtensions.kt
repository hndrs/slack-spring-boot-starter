package com.kreait.slack.api.contract.jackson.group.conversations

import com.kreait.slack.api.contract.jackson.common.types.Channel
import com.kreait.slack.api.contract.jackson.group.channels.sample

fun SuccessfulConversationsInfoResponse.Companion.sample(): SuccessfulConversationsInfoResponse {
    return SuccessfulConversationsInfoResponse(true, Channel.sample())
}

fun ErrorConversationsInfoResponse.Companion.sample(): ErrorConversationsInfoResponse {
    return ErrorConversationsInfoResponse(false, "")
}


fun ConversationsInfoRequest.Companion.sample(): ConversationsInfoRequest {
    return ConversationsInfoRequest("")
}
