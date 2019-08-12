package com.kreait.slack.api.contract.jackson.group.conversations

import com.kreait.slack.api.contract.jackson.common.types.Channel
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationCreateRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationCreateResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationCreateResponse

fun ConversationCreateRequest.Companion.sample(): ConversationCreateRequest {
    return ConversationCreateRequest(name = "NewChannelName")
}

fun SuccessfulConversationCreateResponse.Companion.sample(): SuccessfulConversationCreateResponse {
    return SuccessfulConversationCreateResponse(true, Channel.sample())
}

fun ErrorConversationCreateResponse.Companion.sample(): ErrorConversationCreateResponse {
    return ErrorConversationCreateResponse(false, "error")
}
