package com.kreait.slack.api.contract.jackson.group.conversations

import com.kreait.slack.api.contract.jackson.group.conversations.ConversationsLeaveRequest
import com.kreait.slack.api.contract.jackson.group.conversations.ErrorConversationLeaveResponse
import com.kreait.slack.api.contract.jackson.group.conversations.SuccessfulConversationLeaveResponse

fun SuccessfulConversationLeaveResponse.Companion.sample(): SuccessfulConversationLeaveResponse {
    return SuccessfulConversationLeaveResponse(true, false)
}

fun ErrorConversationLeaveResponse.Companion.sample(): ErrorConversationLeaveResponse {
    return ErrorConversationLeaveResponse(false, "")
}


fun ConversationsLeaveRequest.Companion.sample(): ConversationsLeaveRequest {
    return ConversationsLeaveRequest("")
}
