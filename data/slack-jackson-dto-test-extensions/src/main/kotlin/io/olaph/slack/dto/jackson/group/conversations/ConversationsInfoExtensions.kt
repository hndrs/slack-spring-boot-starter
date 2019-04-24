package io.olaph.slack.dto.jackson.group.conversations

import io.olaph.slack.dto.jackson.common.types.Channel
import io.olaph.slack.dto.jackson.group.channels.sample

fun SuccessfulConversationsInfoResponse.Companion.sample(): SuccessfulConversationsInfoResponse {
    return SuccessfulConversationsInfoResponse(true, Channel.sample())
}

fun ErrorConversationsInfoResponse.Companion.sample(): ErrorConversationsInfoResponse {
    return ErrorConversationsInfoResponse(false, "")
}


fun ConversationsInfoRequest.Companion.sample(): ConversationsInfoRequest {
    return ConversationsInfoRequest("")
}
