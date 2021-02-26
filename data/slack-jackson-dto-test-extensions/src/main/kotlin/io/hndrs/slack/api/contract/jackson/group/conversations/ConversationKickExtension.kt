package io.hndrs.slack.api.contract.jackson.group.conversations

fun SuccessfulConversationKickResponse.Companion.sample() = SuccessfulConversationKickResponse(ok = true)

fun ErrorConversationKickResponse.Companion.sample() = ErrorConversationKickResponse(ok = false, error = "")

fun ConversationsKickRequest.Companion.sample() = ConversationsKickRequest(channel = "", user = "")
