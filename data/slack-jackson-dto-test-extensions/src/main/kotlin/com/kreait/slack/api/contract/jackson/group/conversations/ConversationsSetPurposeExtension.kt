package com.kreait.slack.api.contract.jackson.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsSetPurposeRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationSetPurposeResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationSetPurposeResponse

fun ConversationsSetPurposeRequest.Companion.sample() = ConversationsSetPurposeRequest("", "")

fun SuccessfulConversationSetPurposeResponse.Companion.sample() = SuccessfulConversationSetPurposeResponse(true, "")

fun ErrorConversationSetPurposeResponse.Companion.sample() = ErrorConversationSetPurposeResponse(false, "")
