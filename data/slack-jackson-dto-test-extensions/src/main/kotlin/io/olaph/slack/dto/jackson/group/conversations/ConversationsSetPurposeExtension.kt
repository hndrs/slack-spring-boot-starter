package io.olaph.slack.dto.jackson.group.conversations

fun ConversationsSetPurposeRequest.Companion.sample() = ConversationsSetPurposeRequest("", "")

fun SuccessfulConversationSetPurposeResponse.Companion.sample() = SuccessfulConversationSetPurposeResponse(true, "")

fun ErrorConversationSetPurposeResponse.Companion.sample() = ErrorConversationSetPurposeResponse(false, "")