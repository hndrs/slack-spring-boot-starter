package com.kreait.slack.api.contract.jackson.group.conversations

import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.common.sample
import java.time.Instant

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
    return SuccessfulConversationRepliesResponse.Message("", Instant.ofEpochSecond(10000), "", "")
}

