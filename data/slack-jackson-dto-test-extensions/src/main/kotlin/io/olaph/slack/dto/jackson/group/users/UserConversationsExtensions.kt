package io.olaph.slack.dto.jackson.group.users

import io.olaph.slack.dto.jackson.ChannelType
import io.olaph.slack.dto.jackson.common.ResponseMetadata
import io.olaph.slack.dto.jackson.common.sample

fun SuccessfulUserConversationsResponse.Companion.sample(): SuccessfulUserConversationsResponse {
    return SuccessfulUserConversationsResponse(true, listOf(), ResponseMetadata.sample())
}

fun ErrorUserConversationsResponse.Companion.sample(): ErrorUserConversationsResponse {
    return ErrorUserConversationsResponse(false, "")
}

fun SlackUserConversationListRequest.Companion.sample(): SlackUserConversationListRequest {
    return SlackUserConversationListRequest("", true, 0, setOf(ChannelType.IM), "")
}
