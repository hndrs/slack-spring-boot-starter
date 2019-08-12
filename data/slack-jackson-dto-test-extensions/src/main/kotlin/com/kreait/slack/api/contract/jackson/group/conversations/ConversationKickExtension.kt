package com.kreait.slack.api.contract.jackson.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsKickRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationKickResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationKickResponse

fun SuccessfulConversationKickResponse.Companion.sample() = SuccessfulConversationKickResponse(ok = true)

fun ErrorConversationKickResponse.Companion.sample() = ErrorConversationKickResponse(ok = false, error = "")

fun ConversationsKickRequest.Companion.sample() = ConversationsKickRequest(channel = "", user = "")
