package io.hndrs.slack.api.contract.jackson.group.conversations

import io.hndrs.slack.api.contract.jackson.common.ResponseMetadata
import io.hndrs.slack.api.contract.jackson.common.sample

fun SuccessfulConversationListResponse.Companion.sample(): SuccessfulConversationListResponse {
    return SuccessfulConversationListResponse(true, listOf(), ResponseMetadata.sample())
}

fun ErrorConversationListResponse.Companion.sample(): ErrorConversationListResponse {
    return ErrorConversationListResponse(false, "")
}


fun ConversationsListRequest.Companion.sample(): ConversationsListRequest {
    return ConversationsListRequest("", true)
}
