package io.olaph.slack.dto.jackson.group.conversations

fun SuccessfulConversationListResponse.Companion.sample(): SuccessfulConversationListResponse {
    return SuccessfulConversationListResponse(true, listOf(), ResponseMetadata.sample())
}

fun ErrorConversationListResponse.Companion.sample(): ErrorConversationListResponse {
    return ErrorConversationListResponse(false, "")
}


fun ConversationsListRequest.Companion.sample(): ConversationsListRequest {
    return ConversationsListRequest("", true)
}
