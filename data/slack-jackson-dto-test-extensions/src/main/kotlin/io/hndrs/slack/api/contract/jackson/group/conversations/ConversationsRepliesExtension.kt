package io.hndrs.slack.api.contract.jackson.group.conversations

import io.hndrs.slack.api.contract.jackson.common.ResponseMetadata
import io.hndrs.slack.api.contract.jackson.common.sample
import io.hndrs.slack.api.contract.jackson.common.types.Message
import io.hndrs.slack.api.contract.jackson.group.chat.sample

fun ConversationsRepliesRequest.Companion.sample(): ConversationsRepliesRequest {
    return ConversationsRepliesRequest("", "")
}

fun SuccessfulConversationRepliesResponse.Companion.sample(): SuccessfulConversationRepliesResponse {
    return SuccessfulConversationRepliesResponse(true, listOf(Message.sample()), false, ResponseMetadata.sample())
}

fun ErrorConversationRepliesResponse.Companion.sample(): ErrorConversationRepliesResponse {
    return ErrorConversationRepliesResponse(true, "")
}

