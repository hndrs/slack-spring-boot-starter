package com.kreait.slack.api.contract.jackson.group.conversations

import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.common.sample
import com.kreait.slack.api.contract.jackson.common.types.Message
import com.kreait.slack.api.contract.jackson.group.chat.sample

fun ConversationsHistoryRequest.Companion.sample() = ConversationsHistoryRequest("", "")

fun SuccessfulConversationHistoryResponse.Companion.sample() =
        SuccessfulConversationHistoryResponse(true, listOf(Message.sample()), false, 0, ResponseMetadata.sample())

fun ErrorConversationHistoryResponse.Companion.sample() = ErrorConversationHistoryResponse(false, "")
