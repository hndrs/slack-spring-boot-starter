package io.hndrs.slack.api.contract.jackson.group.conversations

import io.hndrs.slack.api.contract.jackson.common.types.Channel
import io.hndrs.slack.api.contract.jackson.common.types.sample

fun ConversationCreateRequest.Companion.sample(): ConversationCreateRequest {
    return ConversationCreateRequest(name = "NewChannelName")
}

fun SuccessfulConversationCreateResponse.Companion.sample(): SuccessfulConversationCreateResponse {
    return SuccessfulConversationCreateResponse(true, Channel.sample())
}

fun ErrorConversationCreateResponse.Companion.sample(): ErrorConversationCreateResponse {
    return ErrorConversationCreateResponse(false, "error")
}
