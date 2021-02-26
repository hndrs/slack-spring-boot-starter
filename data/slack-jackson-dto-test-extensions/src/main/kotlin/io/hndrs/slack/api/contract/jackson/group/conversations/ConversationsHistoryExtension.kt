package io.hndrs.slack.api.contract.jackson.group.conversations

import io.hndrs.slack.api.contract.jackson.common.ResponseMetadata
import io.hndrs.slack.api.contract.jackson.common.sample
import io.hndrs.slack.api.contract.jackson.common.types.Message
import io.hndrs.slack.api.contract.jackson.group.chat.sample

fun ConversationsHistoryRequest.Companion.sample() = ConversationsHistoryRequest("", "")

fun SuccessfulConversationHistoryResponse.Companion.sample() =
    SuccessfulConversationHistoryResponse(true, listOf(Message.sample()), false, 0, ResponseMetadata.sample())

fun ErrorConversationHistoryResponse.Companion.sample() = ErrorConversationHistoryResponse(false, "")
