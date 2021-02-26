package io.hndrs.slack.api.contract.jackson.group.users

import io.hndrs.slack.api.contract.jackson.ChannelType
import io.hndrs.slack.api.contract.jackson.common.ResponseMetadata
import io.hndrs.slack.api.contract.jackson.common.sample

fun SuccessfulConversationsResponse.Companion.sample(): SuccessfulConversationsResponse {
    return SuccessfulConversationsResponse(true, listOf(), ResponseMetadata.sample())
}

fun ErrorConversationsResponse.Companion.sample(): ErrorConversationsResponse {
    return ErrorConversationsResponse(false, "")
}

fun ConversationsRequest.Companion.sample(): ConversationsRequest {
    return ConversationsRequest("", true, 0, setOf(ChannelType.IM), "")
}
