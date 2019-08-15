package com.kreait.slack.api.contract.jackson.group.chat

import com.kreait.slack.api.contract.jackson.common.InstantSample

fun ChatMeMessageRequest.Companion.sample(): ChatMeMessageRequest {
    return ChatMeMessageRequest("", "")
}

fun SuccessfulChatMeMessageResponse.Companion.sample(): SuccessfulChatMeMessageResponse {
    return SuccessfulChatMeMessageResponse(true, "", InstantSample.sample())
}

fun ErrorChatMeMessageResponse.Companion.sample(): ErrorChatMeMessageResponse {
    return ErrorChatMeMessageResponse(false, "")
}
