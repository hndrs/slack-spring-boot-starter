package com.kreait.slack.api.contract.jackson.group.conversations

import com.kreait.slack.api.contract.jackson.common.types.Channel
import com.kreait.slack.api.contract.jackson.group.channels.sample

fun SuccessfulConversationInviteResponse.Companion.sample(): SuccessfulConversationInviteResponse {
    return SuccessfulConversationInviteResponse(true, Channel.sample())
}

fun ErrorConversationInviteResponse.Companion.sample(): ErrorConversationInviteResponse {
    return ErrorConversationInviteResponse(false, "")
}


fun ConversationsInviteRequest.Companion.sample(): ConversationsInviteRequest {
    return ConversationsInviteRequest("", listOf(""))
}
