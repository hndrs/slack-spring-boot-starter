package com.kreait.slack.api.contract.jackson.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationUnarchiveRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationUnarchiveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationUnarchiveResponse

fun ConversationUnarchiveRequest.Companion.sample(): ConversationUnarchiveRequest {
    return ConversationUnarchiveRequest("")
}

fun SuccessfulConversationUnarchiveResponse.Companion.sample(): SuccessfulConversationUnarchiveResponse {
    return SuccessfulConversationUnarchiveResponse(true)
}

fun ErrorConversationUnarchiveResponse.Companion.sample(): ErrorConversationUnarchiveResponse {
    return ErrorConversationUnarchiveResponse(true, "")
}




