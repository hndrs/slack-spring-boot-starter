package com.kreait.slack.api.contract.jackson.group.users

import com.kreait.slack.api.contract.jackson.ChannelType
import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.common.sample

fun SuccessfulUserConversationsResponse.Companion.sample(): SuccessfulUserConversationsResponse {
    return SuccessfulUserConversationsResponse(true, listOf(), ResponseMetadata.sample())
}

fun ErrorUserConversationsResponse.Companion.sample(): ErrorUserConversationsResponse {
    return ErrorUserConversationsResponse(false, "")
}

fun SlackUserConversationListRequest.Companion.sample(): SlackUserConversationListRequest {
    return SlackUserConversationListRequest("", true, 0, setOf(ChannelType.IM), "")
}
