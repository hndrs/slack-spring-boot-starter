package com.kreait.slack.api.contract.jackson.group.conversations

import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.common.sample

fun SuccessfulConversationMembersResponse.Companion.sample(): SuccessfulConversationMembersResponse {
    return SuccessfulConversationMembersResponse(true, listOf(), ResponseMetadata.sample())
}

fun ErrorConversationMembersResponse.Companion.sample(): ErrorConversationMembersResponse {
    return ErrorConversationMembersResponse(false, "")
}

fun ConversationMembersRequest.Companion.sample(): ConversationMembersRequest {
    return ConversationMembersRequest("", "", 100)
}
