package io.olaph.slack.dto.jackson.group.conversations

import io.olaph.slack.dto.jackson.common.types.Channel
import io.olaph.slack.dto.jackson.group.channels.sample

fun ConversationsRenameRequest.Companion.sample(): ConversationsRenameRequest {
    return ConversationsRenameRequest("", "")
}

fun SuccessfulConversationsRenameResponse.Companion.sample(): SuccessfulConversationsRenameResponse {
    return SuccessfulConversationsRenameResponse(true, Channel.sample())
}

fun ErrorConversationsRenameResponse.Companion.sample(): ErrorConversationsRenameResponse {
    return ErrorConversationsRenameResponse(true, "")
}

