package io.olaph.slack.dto.jackson.group.conversations

import io.olaph.slack.dto.jackson.common.ResponseMetadata
import io.olaph.slack.dto.jackson.common.sample

fun ConversationsHistoryRequest.Companion.sample() = ConversationsHistoryRequest("", "")

fun SuccessfulConversationHistoryResponse.Companion.sample() =
        SuccessfulConversationHistoryResponse(true, listOf(Message.sample()), false, 0, ResponseMetadata.sample())

fun ErrorConversationHistoryResponse.Companion.sample() = ErrorConversationHistoryResponse(false, "")

fun Message.Companion.sample(): Message {
    return Message("", "", "", "", listOf(Message.Attachment.sample()))
}

fun Message.Attachment.Companion.sample(): Message.Attachment {
    return Message.Attachment("", "", "", "", 1080, 720, 1)
}
