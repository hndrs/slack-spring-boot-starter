package io.hndrs.slack.api.contract.jackson.group.conversations

fun SuccessfulConversationLeaveResponse.Companion.sample(): SuccessfulConversationLeaveResponse {
    return SuccessfulConversationLeaveResponse(true, false)
}

fun ErrorConversationLeaveResponse.Companion.sample(): ErrorConversationLeaveResponse {
    return ErrorConversationLeaveResponse(false, "")
}


fun ConversationsLeaveRequest.Companion.sample(): ConversationsLeaveRequest {
    return ConversationsLeaveRequest("")
}
