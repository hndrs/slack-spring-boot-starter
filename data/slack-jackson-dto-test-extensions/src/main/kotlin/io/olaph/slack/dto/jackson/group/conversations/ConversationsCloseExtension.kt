package io.olaph.slack.dto.jackson.group.conversations

import io.olaph.slack.dto.jackson.group.conversations.ConversationCloseRequest
import io.olaph.slack.dto.jackson.group.conversations.ErrorConversationCloseResponse
import io.olaph.slack.dto.jackson.group.conversations.SuccessfulConversationCloseResponse

fun ConversationCloseRequest.Companion.sample() =
        ConversationCloseRequest(channel = "31232132131")

fun SuccessfulConversationCloseResponse.Companion.sample() =
        SuccessfulConversationCloseResponse(ok = true, noOp = false, alreadyClosed = false)

fun ErrorConversationCloseResponse.Companion.sample() =
        ErrorConversationCloseResponse(ok = false, error = "error")
