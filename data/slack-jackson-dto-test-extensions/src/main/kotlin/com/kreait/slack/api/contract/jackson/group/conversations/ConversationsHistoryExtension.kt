package com.kreait.slack.api.contract.jackson.group.conversations

import com.kreait.slack.api.contract.jackson.common.ResponseMetadata
import com.kreait.slack.api.contract.jackson.common.sample
import java.time.Instant

fun ConversationsHistoryRequest.Companion.sample() = ConversationsHistoryRequest("", "")

fun SuccessfulConversationHistoryResponse.Companion.sample() =
        SuccessfulConversationHistoryResponse(true, listOf(Message.sample()), false, 0, ResponseMetadata.sample())

fun ErrorConversationHistoryResponse.Companion.sample() = ErrorConversationHistoryResponse(false, "")

fun Message.Companion.sample(): Message {
    return Message("", "", "", Instant.now(), listOf(Message.Attachment.sample()))
}

fun Message.Attachment.Companion.sample(): Message.Attachment {
    return Message.Attachment("", "", "", "", 1080, 720, 1)
}
