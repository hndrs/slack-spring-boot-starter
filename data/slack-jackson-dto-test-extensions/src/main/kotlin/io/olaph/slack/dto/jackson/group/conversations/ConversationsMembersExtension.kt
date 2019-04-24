package io.olaph.slack.dto.jackson.group.conversations

import io.olaph.slack.dto.jackson.common.ResponseMetadata
import io.olaph.slack.dto.jackson.common.sample

fun SuccessfulConversationMembersResponse.Companion.sample(): SuccessfulConversationMembersResponse {
    return SuccessfulConversationMembersResponse(true, listOf(), ResponseMetadata.sample())
}

fun ErrorConversationMembersResponse.Companion.sample(): ErrorConversationMembersResponse {
    return ErrorConversationMembersResponse(false, "")
}

fun ConversationMembersRequest.Companion.sample(): ConversationMembersRequest {
    return ConversationMembersRequest("", "", 100)
}
