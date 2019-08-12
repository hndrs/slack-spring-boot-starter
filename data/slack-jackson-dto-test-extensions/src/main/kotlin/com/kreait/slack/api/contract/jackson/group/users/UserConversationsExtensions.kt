package com.kreait.slack.api.contract.jackson.group.users

import com.kreait.slack.api.contract.jackson.ChannelType
import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.common.sample

fun SuccessfulConversationsResponse.Companion.sample(): SuccessfulConversationsResponse {
    return SuccessfulConversationsResponse(true, listOf(), ResponseMetadata.sample())
}

fun ErrorConversationsResponse.Companion.sample(): ErrorConversationsResponse {
    return ErrorConversationsResponse(false, "")
}

fun ConversationsRequest.Companion.sample(): ConversationsRequest {
    return ConversationsRequest("", true, 0, setOf(ChannelType.IM), "")
}
