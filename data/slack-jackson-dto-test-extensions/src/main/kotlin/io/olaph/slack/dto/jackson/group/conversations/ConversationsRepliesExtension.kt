package io.olaph.slack.dto.jackson.group.conversations

import io.olaph.slack.dto.jackson.common.ResponseMetadata
import io.olaph.slack.dto.jackson.common.sample

fun ConversationsRepliesRequest.Companion.sample(): ConversationsRepliesRequest {
    return ConversationsRepliesRequest("", "")
}

fun SuccessfulConversationRepliesResponse.Companion.sample(): SuccessfulConversationRepliesResponse {
    return SuccessfulConversationRepliesResponse(true, listOf(SuccessfulConversationRepliesResponse.Message.sample()), false, ResponseMetadata.sample())
}

fun ErrorConversationRepliesResponse.Companion.sample(): ErrorConversationRepliesResponse {
    return ErrorConversationRepliesResponse(true, "")
}

fun SuccessfulConversationRepliesResponse.Message.Companion.sample(): SuccessfulConversationRepliesResponse.Message {
    return SuccessfulConversationRepliesResponse.Message("", "", "", "")
}

