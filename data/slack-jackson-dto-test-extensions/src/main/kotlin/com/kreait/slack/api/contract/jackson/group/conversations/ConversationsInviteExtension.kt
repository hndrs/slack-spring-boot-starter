package com.kreait.slack.api.contract.jackson.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsInviteRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationInviteResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationInviteResponse
import com.kreait.slack.api.contract.jackson.common.types.Channel

fun SuccessfulConversationInviteResponse.Companion.sample(): SuccessfulConversationInviteResponse {
    return SuccessfulConversationInviteResponse(true, Channel.sample())
}

fun ErrorConversationInviteResponse.Companion.sample(): ErrorConversationInviteResponse {
    return ErrorConversationInviteResponse(false, "")
}


fun ConversationsInviteRequest.Companion.sample(): ConversationsInviteRequest {
    return ConversationsInviteRequest("", listOf(""))
}
