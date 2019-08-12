package com.kreait.slack.api.contract.jackson.group.conversations

fun ConversationsSetPurposeRequest.Companion.sample() = ConversationsSetPurposeRequest("", "")

fun SuccessfulConversationSetPurposeResponse.Companion.sample() = SuccessfulConversationSetPurposeResponse(true, "")

fun ErrorConversationSetPurposeResponse.Companion.sample() = ErrorConversationSetPurposeResponse(false, "")
