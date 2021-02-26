package io.hndrs.slack.api.contract.jackson.group.conversations

import io.hndrs.slack.api.contract.jackson.common.ResponseMetadata
import io.hndrs.slack.api.contract.jackson.common.sample
import io.hndrs.slack.api.contract.jackson.common.types.Channel
import io.hndrs.slack.api.contract.jackson.common.types.sample

fun ConversationsRenameRequest.Companion.sample(): ConversationsRenameRequest {
    return ConversationsRenameRequest("", "")
}

fun SuccessfulConversationsRenameResponse.Companion.sample(): SuccessfulConversationsRenameResponse {
    return SuccessfulConversationsRenameResponse(true, Channel.sample(), ResponseMetadata.sample())
}

fun ErrorConversationsRenameResponse.Companion.sample(): ErrorConversationsRenameResponse {
    return ErrorConversationsRenameResponse(true, "")
}

