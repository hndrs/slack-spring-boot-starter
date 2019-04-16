package io.olaph.slack.dto.jackson.group.conversations

import io.olaph.slack.dto.jackson.common.types.Channel
import io.olaph.slack.dto.jackson.group.channels.sample

fun SuccessfulConversationInviteResponse.Companion.sample(): SuccessfulConversationInviteResponse {
    return SuccessfulConversationInviteResponse(true, Channel.sample())
}

fun ErrorConversationInviteResponse.Companion.sample(): ErrorConversationInviteResponse {
    return ErrorConversationInviteResponse(false, "")
}


fun ConversationsInviteRequest.Companion.sample(): ConversationsInviteRequest {
    return ConversationsInviteRequest("", listOf(""))
}
