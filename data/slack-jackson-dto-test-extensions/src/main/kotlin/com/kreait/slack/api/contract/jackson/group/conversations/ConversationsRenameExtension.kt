package com.kreait.slack.api.contract.jackson.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsRenameRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationsRenameResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationsRenameResponse
import com.kreait.slack.api.contract.jackson.common.types.Channel

fun ConversationsRenameRequest.Companion.sample(): ConversationsRenameRequest {
    return ConversationsRenameRequest("", "")
}

fun SuccessfulConversationsRenameResponse.Companion.sample(): SuccessfulConversationsRenameResponse {
    return SuccessfulConversationsRenameResponse(true, Channel.sample())
}

fun ErrorConversationsRenameResponse.Companion.sample(): ErrorConversationsRenameResponse {
    return ErrorConversationsRenameResponse(true, "")
}

