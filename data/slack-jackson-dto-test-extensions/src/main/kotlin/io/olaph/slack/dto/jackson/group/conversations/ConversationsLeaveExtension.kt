package io.olaph.slack.dto.jackson.group.conversations

fun SuccessfulConversationLeaveResponse.Companion.sample(): SuccessfulConversationLeaveResponse {
    return SuccessfulConversationLeaveResponse(true, false)
}

fun ErrorConversationLeaveResponse.Companion.sample(): ErrorConversationLeaveResponse {
    return ErrorConversationLeaveResponse(false, "")
}


fun ConversationsLeaveRequest.Companion.sample(): ConversationsLeaveRequest {
    return ConversationsLeaveRequest("")
}
