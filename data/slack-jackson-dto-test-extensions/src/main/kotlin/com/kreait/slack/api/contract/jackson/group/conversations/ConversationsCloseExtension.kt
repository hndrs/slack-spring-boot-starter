package com.kreait.slack.api.contract.jackson.group.conversations

fun ConversationCloseRequest.Companion.sample() =
        ConversationCloseRequest(channel = "31232132131")

fun SuccessfulConversationCloseResponse.Companion.sample() =
        SuccessfulConversationCloseResponse(ok = true, noOp = false, alreadyClosed = false)

fun ErrorConversationCloseResponse.Companion.sample() =
        ErrorConversationCloseResponse(ok = false, error = "error")
