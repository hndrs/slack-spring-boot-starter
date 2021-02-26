package io.hndrs.slack.api.contract.jackson.group.conversations

import io.hndrs.slack.api.contract.jackson.common.types.Channel
import io.hndrs.slack.api.contract.jackson.common.types.sample

fun SuccessfulConversationsInfoResponse.Companion.sample(): SuccessfulConversationsInfoResponse {
    return SuccessfulConversationsInfoResponse(true, Channel.sample())
}

fun ErrorConversationsInfoResponse.Companion.sample(): ErrorConversationsInfoResponse {
    return ErrorConversationsInfoResponse(false, "")
}


fun ConversationsInfoRequest.Companion.sample(): ConversationsInfoRequest {
    return ConversationsInfoRequest("")
}
