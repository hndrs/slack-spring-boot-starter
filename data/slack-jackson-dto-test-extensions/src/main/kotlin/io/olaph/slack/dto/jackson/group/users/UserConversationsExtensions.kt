package io.olaph.slack.dto.jackson.group.users

fun SuccessfulUserConversationsResponse.Companion.sample(): SuccessfulUserConversationsResponse {
    return SuccessfulUserConversationsResponse(true, listOf(), SuccessfulUserConversationsResponse.ResponseMetadata.sample())
}

fun SuccessfulUserConversationsResponse.ResponseMetadata.Companion.sample(): SuccessfulUserConversationsResponse.ResponseMetadata {
    return SuccessfulUserConversationsResponse.ResponseMetadata("")
}

fun ErrorUserConversationsResponse.Companion.sample(): ErrorUserConversationsResponse {
    return ErrorUserConversationsResponse(false, "")
}