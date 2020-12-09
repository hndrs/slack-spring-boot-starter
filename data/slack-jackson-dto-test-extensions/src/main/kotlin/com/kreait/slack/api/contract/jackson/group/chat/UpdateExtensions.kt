package com.kreait.slack.api.contract.jackson.group.chat

import com.kreait.slack.api.contract.jackson.common.InstantSample

fun ErrorChatUpdateResponse.Companion.sample(): ErrorChatUpdateResponse {
    return ErrorChatUpdateResponse(false, "")
}

fun SuccessfulChatUpdateResponse.Companion.sample(): SuccessfulChatUpdateResponse {
    return SuccessfulChatUpdateResponse(true, "", InstantSample.sample(), "")
}

fun ChatUpdateRequest.Companion.sample(): ChatUpdateRequest {
    return ChatUpdateRequest("", "", InstantSample.sample(), false, listOf(), false)
}
