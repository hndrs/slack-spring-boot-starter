package com.kreait.slack.api.contract.jackson.group.conversations

import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.common.sample
import com.kreait.slack.api.contract.jackson.common.types.Channel
import com.kreait.slack.api.contract.jackson.common.types.sample
import com.kreait.slack.api.contract.jackson.group.channels.sample

fun ConversationsRenameRequest.Companion.sample(): ConversationsRenameRequest {
    return ConversationsRenameRequest("", "")
}

fun SuccessfulConversationsRenameResponse.Companion.sample(): SuccessfulConversationsRenameResponse {
    return SuccessfulConversationsRenameResponse(true, Channel.sample(), ResponseMetadata.sample())
}

fun ErrorConversationsRenameResponse.Companion.sample(): ErrorConversationsRenameResponse {
    return ErrorConversationsRenameResponse(true, "")
}

