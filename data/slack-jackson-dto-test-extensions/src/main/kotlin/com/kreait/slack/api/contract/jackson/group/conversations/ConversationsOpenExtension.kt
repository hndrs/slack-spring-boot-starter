package com.kreait.slack.api.contract.jackson.group.conversations

fun ConversationsOpenRequest.Companion.sample(): ConversationsOpenRequest {
    return ConversationsOpenRequest("", false, listOf())
}

fun SuccessfulConversationOpenResponse.Companion.sample(): SuccessfulConversationOpenResponse {
    return SuccessfulConversationOpenResponse(true, SuccessfulConversationOpenResponse.Channel.sample())
}

fun ErrorConversationOpenResponse.Companion.sample(): ErrorConversationOpenResponse {
    return ErrorConversationOpenResponse(true, "")
}

fun SuccessfulConversationOpenResponse.Channel.Companion.sample(): SuccessfulConversationOpenResponse.Channel {
    return SuccessfulConversationOpenResponse.Channel("")
}

