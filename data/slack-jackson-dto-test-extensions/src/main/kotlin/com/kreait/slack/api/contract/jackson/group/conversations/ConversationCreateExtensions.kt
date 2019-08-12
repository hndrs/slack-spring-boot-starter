package com.kreait.slack.api.contract.jackson.group.conversations

import com.kreait.slack.api.contract.jackson.common.types.Channel
import com.kreait.slack.api.contract.jackson.group.channels.sample

fun ConversationCreateRequest.Companion.sample(): ConversationCreateRequest {
    return ConversationCreateRequest(name = "NewChannelName")
}

fun SuccessfulConversationCreateResponse.Companion.sample(): SuccessfulConversationCreateResponse {
    return SuccessfulConversationCreateResponse(true, Channel.sample())
}

fun ErrorConversationCreateResponse.Companion.sample(): ErrorConversationCreateResponse {
    return ErrorConversationCreateResponse(false, "error")
}
