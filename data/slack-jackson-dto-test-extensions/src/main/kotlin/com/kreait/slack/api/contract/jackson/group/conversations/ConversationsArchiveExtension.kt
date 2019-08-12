package com.kreait.slack.api.contract.jackson.group.conversations

fun ConversationArchiveRequest.Companion.sample(): ConversationArchiveRequest {
    return ConversationArchiveRequest("")
}

fun SuccessfulConversationArchiveResponse.Companion.sample(): SuccessfulConversationArchiveResponse {
    return SuccessfulConversationArchiveResponse(true)
}

fun ErrorConversationArchiveResponse.Companion.sample(): ErrorConversationArchiveResponse {
    return ErrorConversationArchiveResponse(true, "")
}




