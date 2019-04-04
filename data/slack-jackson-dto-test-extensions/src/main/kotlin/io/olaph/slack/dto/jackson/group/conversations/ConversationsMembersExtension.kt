package io.olaph.slack.dto.jackson.group.conversations

fun SuccessfulConversationMembersResponse.Companion.sample(): SuccessfulConversationMembersResponse {
    return SuccessfulConversationMembersResponse(true, listOf(), ResponseMetadata.sample())
}

fun ErrorConversationMembersResponse.Companion.sample(): ErrorConversationMembersResponse {
    return ErrorConversationMembersResponse(false, "")
}

fun ResponseMetadata.Companion.sample(): ResponseMetadata {
    return ResponseMetadata("")
}

fun ConversationMembersRequest.Companion.sample(): ConversationMembersRequest {
    return ConversationMembersRequest("", "", 100)
}
