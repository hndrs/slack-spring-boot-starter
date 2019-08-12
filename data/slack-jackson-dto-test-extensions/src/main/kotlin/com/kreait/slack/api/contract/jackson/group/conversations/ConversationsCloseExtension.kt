package com.kreait.slack.api.contract.jackson.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationCloseRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationCloseResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationCloseResponse

fun ConversationCloseRequest.Companion.sample() =
        ConversationCloseRequest(channel = "31232132131")

fun SuccessfulConversationCloseResponse.Companion.sample() =
        SuccessfulConversationCloseResponse(ok = true, noOp = false, alreadyClosed = false)

fun ErrorConversationCloseResponse.Companion.sample() =
        ErrorConversationCloseResponse(ok = false, error = "error")
