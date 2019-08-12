package com.kreait.slack.api.contract.jackson.group.conversations

import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsListRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationListResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationListResponse
import com.kreait.slack.api.contract.jackson.common.sample

fun SuccessfulConversationListResponse.Companion.sample(): SuccessfulConversationListResponse {
    return SuccessfulConversationListResponse(true, listOf(), ResponseMetadata.sample())
}

fun ErrorConversationListResponse.Companion.sample(): ErrorConversationListResponse {
    return ErrorConversationListResponse(false, "")
}


fun ConversationsListRequest.Companion.sample(): ConversationsListRequest {
    return ConversationsListRequest("", true)
}
